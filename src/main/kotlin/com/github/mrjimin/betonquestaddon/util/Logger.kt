package com.github.mrjimin.betonquestaddon.util

import org.bukkit.Bukkit

object Logger {
    fun info(string: String) {
        Bukkit.getConsoleSender().sendMessage("[BetonQuestAddon] $string".toMMComponent())
    }
}