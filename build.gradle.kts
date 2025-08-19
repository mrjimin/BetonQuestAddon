import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar

plugins {
    java
    kotlin("jvm") version "2.2.0"
    id("com.gradleup.shadow") version "9.0.0-beta10"
}

group = "com.github.mrjimin"
version = "1.3.0"

repositories {
    mavenCentral()
    maven {
        name = "papermc"
        url = uri("https://repo.papermc.io/repository/maven-public/")
    }

    maven("https://repo.nexomc.com/releases")
    maven("https://jitpack.io")
    maven("https://repo.codemc.org/repository/maven-public/")
    maven("https://repo.nightexpressdev.com/releases")
    maven("https://repo.momirealms.net/releases/")
    maven("https://repo.extendedclip.com/content/repositories/placeholderapi/")
    maven("https://repo.xenondevs.xyz/releases")
    // maven("https://nexus.frengor.com/repository/public/")
    maven("https://maven.enginehub.org/repo/")
    // maven("https://repo.plasmoverse.com/releases")
    maven("https://repo.plo.su")
}

dependencies {
    compileOnly("io.papermc.paper:paper-api:1.21.8-R0.1-SNAPSHOT")
    compileOnly("com.nexomc:nexo:1.8.0")
    compileOnly("com.github.LoneDev6","api-itemsadder","2.1.25")
    compileOnly("su.nightexpress.coinsengine","CoinsEngine","2.5.0")
    compileOnly("net.momirealms:craft-engine-core:0.0.59")
    compileOnly("net.momirealms:craft-engine-bukkit:0.0.59")
    compileOnly("com.github.angeschossen:LandsAPI:7.15.20")
    // compileOnly("com.arcaniax:HeadDatabase-API:1.3.2")
    compileOnly("net.momirealms:custom-crops:3.6.40")
    compileOnly("me.clip:placeholderapi:2.11.6")
    // compileOnly("com.frengor:ultimateadvancementapi:2.6.0")
    compileOnly("com.sk89q.worldguard:worldguard-bukkit:7.0.14")
    compileOnly("com.sk89q.worldedit:worldedit-bukkit:7.3.14")
    compileOnly("com.sk89q.worldedit:worldedit-core:7.3.14")
    // compileOnly("su.plo.voice.api:server:2.1.5")
    compileOnly("su.plo:pv-addon-lavaplayer-lib:1.0.7")

    implementation("org.jetbrains.kotlin:kotlin-stdlib:2.2.0") // stdlib 포함
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8:2.2.0") // PlasmoVoice 호환
    implementation("dev.jorel:commandapi-bukkit-shade-mojang-mapped:10.1.2")
    implementation("xyz.xenondevs.invui:invui:2.0.0-alpha.17")
    implementation("xyz.xenondevs.invui:invui-kotlin:2.0.0-alpha.17")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.10.2")

    compileOnly(fileTree("lib") {
        include("*.jar")
    })
}

tasks.build {
    dependsOn("shadowJar")
}

tasks.withType<ShadowJar> {
    exclude("kotlin/**")
    exclude("kotlinx/**")
    exclude("org/**")

    from("LICENSE")

    relocate("dev.jorel.commandapi", "com.github.mrjimin.betonquestaddon.lib.commandapi")

    manifest {
        attributes["paperweight-mappings-namespace"] = "mojang"
    }

    archiveFileName.set("${rootProject.name}-${rootProject.version}.jar")
    destinationDirectory=file("C:\\Users\\aa990\\OneDrive\\바탕 화면\\BetonQuestTest\\plugins")
    // destinationDirectory=file("C:\\Users\\aa990\\OneDrive\\바탕 화면\\BQ_CraftEngine\\plugins")
}

tasks.processResources {
    val props = mapOf("version" to version)
    inputs.properties(props)
    filteringCharset = "UTF-8"
    filesMatching("plugin.yml") { expand(props) }
    filesMatching("paper-plugin.yml") { expand(props) }
}

kotlin {
    jvmToolchain(21)
}