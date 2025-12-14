import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar

plugins {
    kotlin("jvm") version "2.2.21"
    id("com.gradleup.shadow") version "9.2.2"
    id("java")
}

group = "com.github.mrjimin"
version = "1.5.2"

repositories {
    mavenCentral()
    maven {
        name = "papermc"
        url = uri("https://repo.papermc.io/repository/maven-public/")
    }

    maven("https://repo.nexomc.com/snapshots")
    maven("https://maven.devs.beer/")
    maven("https://jitpack.io")
    maven("https://repo.nightexpressdev.com/releases")
    maven("https://repo.momirealms.net/releases/")
    maven("https://repo.extendedclip.com/content/repositories/placeholderapi/")
    maven("https://repo.xenondevs.xyz/releases")
    maven("https://maven.enginehub.org/repo/")
    maven("https://nexus.betonquest.org/repository/betonquest/")
}

dependencies {
    compileOnly("io.papermc.paper:paper-api:1.21.8-R0.1-SNAPSHOT")
    compileOnly("com.nexomc:nexo:1.16.0-dev.8") { exclude("*") }
    compileOnly("dev.lone:api-itemsadder:4.0.10")
    compileOnly("su.nightexpress.coinsengine","CoinsEngine","2.5.0")
    compileOnly("net.momirealms:craft-engine-core:0.0.63")
    compileOnly("net.momirealms:craft-engine-bukkit:0.0.63")
    compileOnly("com.github.angeschossen:LandsAPI:7.15.20")
    compileOnly("net.momirealms:custom-crops:3.6.40")
    compileOnly("me.clip:placeholderapi:2.11.6")
    compileOnly("com.sk89q.worldguard:worldguard-bukkit:7.0.14")
    compileOnly("com.sk89q.worldedit:worldedit-bukkit:7.3.14")
    compileOnly("com.sk89q.worldedit:worldedit-core:7.3.14")
    compileOnly("net.momirealms:custom-crops:3.6.40")
    compileOnly("org.betonquest.betonquest:core:1.0.0-SNAPSHOT") { exclude("*") }


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

tasks.register<ShadowJar>("shadowJarPlugin") {
    archiveFileName.set("BetonQuestAddon-${project.version}.jar")

    from(sourceSets.main.get().output)
    configurations = listOf(project.configurations.runtimeClasspath.get())

    exclude("kotlin/**", "kotlinx/**")
    exclude("org/intellij/**")
    exclude("org/jetbrains/**")
    exclude("org/slf4j/**")

    from("LICENSE")

    manifest {
        attributes["paperweight-mappings-namespace"] = "mojang"
    }

}

tasks {
    build {
        dependsOn("shadowJarPlugin")
    }

    compileJava {
        options.encoding = "UTF-8"
        options.release.set(21)
    }

    processResources {
        val props = mapOf("version" to version)
        inputs.properties(props)
        filteringCharset = "UTF-8"
        filesMatching("paper-plugin.yml") {
            expand(props)
        }
    }
}