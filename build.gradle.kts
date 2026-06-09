import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar
import io.papermc.hangarpublishplugin.model.Platforms
import net.minecrell.pluginyml.bukkit.BukkitPluginDescription

plugins {
    alias(libs.plugins.kotlin.jvm)
    alias(libs.plugins.shadow)
    alias(libs.plugins.hangar.publish)
    alias(libs.plugins.paper.plugin.yml)
}

val isDev: Boolean = false

group = "kr.mrjimin.betonquestaddon"
version = if (isDev) {
    "${libs.versions.project.version.get()}-dev"
} else {
    libs.versions.project.version.get()
}

repositories {
    mavenCentral()
    maven("https://repo.papermc.io/repository/maven-public/")

    maven("https://repo.nexomc.com/releases")
    maven("https://maven.devs.beer/")
    maven("https://jitpack.io")
    maven("https://repo.nightexpressdev.com/releases")
    maven("https://repo.momirealms.net/releases/")
    maven("https://repo.extendedclip.com/content/repositories/placeholderapi/")
    maven("https://repo.xenondevs.xyz/releases")
    maven("https://repo.betonquest.org/betonquest")
    maven("https://repo.hibiscusmc.com/releases")
    maven("https://maven.enginehub.org/repo/")
    maven("https://repo.nekroplex.com/releases")
}

dependencies {
    compileOnly(libs.paper.api.map { "$it-R0.1-SNAPSHOT" })

    compileOnly(libs.nexo) { exclude("*") }
    compileOnly(libs.itemsadder)
    compileOnly(libs.craft.engine.core)
    compileOnly(libs.craft.engine.bukkit)

    compileOnly(libs.custom.crops)
    compileOnly(libs.custom.fishing)
    compileOnly(libs.custom.nameplates)

    compileOnly(libs.betonquest) {
        exclude("de.themoep", "minedown-adventure")
        exclude("com.google.guava", "guava")
        exclude("dev.faststats.metrics", "bukkit")
    }

    compileOnly(libs.hmc.cosmetics)
    compileOnly(libs.worldguard)
    compileOnly(libs.excellent.economy)
    compileOnly(libs.ae.api)

    compileOnly(fileTree("lib") {
        include("*.jar")
    })

    implementation(libs.boosted.yaml)
    implementation(libs.bstats)

    testImplementation(kotlin("test"))
}

kotlin {
    jvmToolchain(25)
}

val shadowJarPlugin = tasks.register<ShadowJar>("shadowJarPlugin") {
    description = "Shadow"
    archiveFileName = "BetonQuestAddon-${project.version}.jar"
    destinationDirectory.set(file("${project.rootDir}/target"))

    from(sourceSets.main.get().output)
    configurations = listOf(project.configurations.runtimeClasspath.get())

    exclude("kotlin/**", "kotlinx/**")

    relocate("org.bstats", "kr.mrjimin.betonquestaddon.libraries.bstats")
    relocate("dev.dejvokep.boostedyaml", "kr.mrjimin.betonquestaddon.libraries.boostedyaml")
}

tasks.named("build") {
    dependsOn(shadowJarPlugin)
}

tasks.compileJava {
    options.encoding = "UTF-8"
    options.release.set(25)
}

// Auto Publish Hangar
hangarPublish {
    publications.register("plugin") {
        version.set(project.version as String)
        channel.set("Snapshot")
        id.set("BetonQuestAddon")
        apiKey.set(System.getenv("HANGAR_API_TOKEN"))
        platforms {
            register(Platforms.PAPER) {
                jar.set(tasks.jar.flatMap { it.archiveFile })

                val versions: List<String> = libs.versions.paper.version.range.get()
                    .split(",")
                    .map { it.trim() }
                platformVersions.set(versions)

                dependencies {
                    url("BetonQuest v3", "https://betonquest.org/3.0-DEV/") {
                        required.set(true)
                    }
                }
            }
        }
    }
}

paper {
    name = "BetonQuestAddon"
    version = project.version.toString()
    authors = listOf("mrjimin")
    website = "https://modrinth.com/plugin/betonquestaddon"
    description = "Extends BetonQuest with additional powerful features."
    apiVersion = "1.21"
    main = "kr.mrjimin.betonquestaddon.BetonQuestAddonPlugin"
    loader = "kr.mrjimin.betonquestaddon.BetonQuestAddonLoader"
    serverDependencies {
        register("BetonQuest") { required = true }
        register("ItemsAdder") { required = false }
        register("Nexo") { required = false }
        register("CraftEngine") { required = false }
        register("CustomCrops") { required = false }
        register("CustomFishing") { required = false }
        register("CustomNameplates") { required = false }
        register("HMCCosmetics") { required = false }
        register("CosmeticsCore") { required = false }
        register("TypeWriter") { required = false }
        register("WorldGuard") { required = false }
        register("ExcellentEconomy") { required = false }
        register("AdvancedEnchantments") { required = false }
    }
    permissions {
        register("betonquestaddon.command") {
            default = BukkitPluginDescription.Permission.Default.OP
        }
    }
}