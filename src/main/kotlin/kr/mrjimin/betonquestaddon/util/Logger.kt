package kr.mrjimin.betonquestaddon.util

import org.bukkit.Bukkit

object Logger {
    fun info(string: String) {
        Bukkit.getConsoleSender().sendMessage("[BetonQuestAddon] $string".toMMComponent())
    }

    fun error(string: String) {
        Bukkit.getConsoleSender().sendMessage("<red>[BetonQuestAddon] $string".toMMComponent())
    }
}