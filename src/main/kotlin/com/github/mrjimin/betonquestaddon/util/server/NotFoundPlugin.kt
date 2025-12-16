package com.github.mrjimin.betonquestaddon.util.server

import org.bukkit.Bukkit

class NotFoundPlugin(plugin: String) : ClassNotFoundException("Can't found plugin!: $plugin")

fun String.checkPlugin(): Boolean = Bukkit.getPluginManager().getPlugin(this) != null