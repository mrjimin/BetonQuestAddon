package com.github.mrjimin.betonquestaddon.util

import org.bukkit.Bukkit
import org.bukkit.plugin.java.JavaPlugin

fun JavaPlugin.info(string: String) {
    Bukkit.getConsoleSender().sendMessage("[BetonQuestAddon] $string".toMMComponent())
}

//fun JavaPlugin.debug(string: String) {
//    if (!config.getBoolean("debug")) return
//    Bukkit.getConsoleSender().sendMessage("<yellow>[BetonQuestAddon] $string".toMMComponent())
//}

fun JavaPlugin.enabledMessage() {
    info("<color:#707070>========================================</color>")
    info("BetonQuestAddon <color:#00d2ff>v${pluginMeta.version}</color>")
    info("${Bukkit.getServer().name} <color:#e3a814>v${Bukkit.getServer().minecraftVersion}</color>")
    info("Status: <color:#50fa7b>Successfully enabled</color>")
    info("<color:#707070>========================================</color>")
}