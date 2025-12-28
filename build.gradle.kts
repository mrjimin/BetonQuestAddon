import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar

plugins {
    kotlin("jvm") version "2.3.0"
    id("com.gradleup.shadow") version "9.2.2"
    id("java")
    `java-library`
}

val git : String = versionBanner()
val builder : String = builder()
ext["git_version"] = git
ext["builder"] = builder

val isDev: Boolean = true

group = "com.github.mrjimin"
version = if (isDev) {
    "${rootProject.properties["project_version"]}-$git-dev"
} else {
    "${rootProject.properties["project_version"]}-$git"
}

repositories {
    mavenCentral()
    maven("https://repo.papermc.io/repository/maven-public/")

    maven("https://repo.nexomc.com/snapshots")
    maven("https://maven.devs.beer/")
    maven("https://jitpack.io")
    maven("https://repo.nightexpressdev.com/releases")
    maven("https://repo.momirealms.net/releases/")
    maven("https://repo.extendedclip.com/content/repositories/placeholderapi/")
    maven("https://repo.xenondevs.xyz/releases")
    maven("https://maven.enginehub.org/repo/")
    maven("https://repo.betonquest.org/betonquest")
    maven("https://repo.hibiscusmc.com/releases")
}

dependencies {
    compileOnly("io.papermc.paper:paper-api:${rootProject.properties["paper_version"]}-R0.1-SNAPSHOT")
    compileOnly("com.nexomc:nexo:${rootProject.properties["nexo_version"]}") { exclude("*") }
    compileOnly("dev.lone:api-itemsadder:${rootProject.properties["itemsadder_version"]}")

    compileOnly("net.momirealms:craft-engine-core:${rootProject.properties["craftengine_version"]}")
    compileOnly("net.momirealms:craft-engine-bukkit:${rootProject.properties["craftengine_version"]}")

    compileOnly("com.github.angeschossen:LandsAPI:${rootProject.properties["lands_version"]}")
    compileOnly("me.clip:placeholderapi:${rootProject.properties["placeholderapi_version"]}")
    compileOnly("com.sk89q.worldguard:worldguard-bukkit:${rootProject.properties["worldguard_version"]}")
    compileOnly("com.sk89q.worldedit:worldedit-bukkit:${rootProject.properties["worldedit_version"]}")
    compileOnly("com.sk89q.worldedit:worldedit-core:${rootProject.properties["worldedit_version"]}")
    compileOnly("net.momirealms:custom-crops:${rootProject.properties["customcrops_version"]}")

    compileOnly("org.betonquest:betonquest:${rootProject.properties["betonquest_version"]}") {
        exclude(group = "de.themoep", module = "minedown-adventure")
    } // { exclude("*") }

    compileOnly("su.nightexpress.coinsengine","CoinsEngine","2.5.0")
    compileOnly("com.hibiscusmc:HMCCosmetics:${rootProject.properties["hmccosmetics_version"]}")

    compileOnly(fileTree("lib") {
        include("*.jar")
    })
}

kotlin {
    jvmToolchain(21)
}

java {
    sourceCompatibility = JavaVersion.VERSION_21
    targetCompatibility = JavaVersion.VERSION_21
}
val shadowJarPlugin = tasks.register<ShadowJar>("shadowJarPlugin") {
    archiveFileName.set("BetonQuestAddon-${project.version}.jar")

    from(sourceSets.main.get().output)
    configurations = listOf(project.configurations.runtimeClasspath.get())

    exclude("kotlin/**", "kotlinx/**")
    exclude("org/intellij/**")
    exclude("org/jetbrains/**")
    exclude("org/slf4j/**")
}

tasks.named("build") {
    // dependsOn(tasks.clean)
    dependsOn(shadowJarPlugin)
}

tasks.compileJava {
    options.encoding = "UTF-8"
    options.release.set(21)
}

tasks.processResources {
    val props = mapOf("version" to version)
    inputs.properties(props)
    filteringCharset = "UTF-8"
    filesMatching("paper-plugin.yml") {
        expand(props)
    }
}

fun versionBanner(): String = project.providers.exec {
    commandLine("git", "rev-parse", "--short=8", "HEAD")
}.standardOutput.asText.map { it.trim() }.getOrElse("Unknown")

fun builder(): String = project.providers.exec {
    commandLine("git", "config", "user.name")
}.standardOutput.asText.map { it.trim() }.getOrElse("Unknown")