package kr.mrjimin.betonquestaddon.util

import org.bukkit.Bukkit

object Logger {
    fun info(string: String) {
        Bukkit.getConsoleSender().sendMessage("[BetonQuestAddon] $string".toMMComponent())
    }

    fun debug(string: String) {
        Bukkit.getConsoleSender().sendMessage("<gray>[BetonQuestAddon] $string".toMMComponent())
    }
}