import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar

plugins {
    kotlin("jvm") version "2.3.0"
    id("com.gradleup.shadow") version "9.2.2"
    id("java")
    `java-library`
}

val isDev: Boolean = true

group = "com.github.mrjimin"
version = if (isDev) {
    "${rootProject.properties["project_version"]}-dev"
} else {
    "${rootProject.properties["project_version"]}"
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
//    maven("https://repo.betonquest.org/betonquest")
    maven("https://repo.hibiscusmc.com/releases")
}

dependencies {
    compileOnly("io.papermc.paper:paper-api:${rootProject.properties["paper_version"]}-R0.1-SNAPSHOT")
    compileOnly("com.nexomc:nexo:${rootProject.properties["nexo_version"]}") { exclude("*") }
    compileOnly("dev.lone:api-itemsadder:${rootProject.properties["itemsadder_version"]}")

    compileOnly("net.momirealms:craft-engine-core:${rootProject.properties["craftengine_version"]}")
    compileOnly("net.momirealms:craft-engine-bukkit:${rootProject.properties["craftengine_version"]}")
    compileOnly("net.momirealms:custom-crops:${rootProject.properties["customcrops_version"]}")

//    compileOnly("org.betonquest:betonquest:${rootProject.properties["betonquest_version"]}") {
//        exclude(group = "de.themoep", module = "minedown-adventure")
//    }

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

    destinationDirectory.set(file("${project.rootDir}/target"))

    from(sourceSets.main.get().output)
    configurations = listOf(project.configurations.runtimeClasspath.get())

    exclude("kotlin/**", "kotlinx/**")
//    exclude("org/intellij/**")
//    exclude("org/jetbrains/**")
//    exclude("org/slf4j/**")
}

tasks.named("build") {
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