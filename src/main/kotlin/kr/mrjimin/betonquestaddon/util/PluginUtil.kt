package kr.mrjimin.betonquestaddon.util

import org.bukkit.Bukkit

private fun getPluginVersion(name: String): String? =
    Bukkit.getPluginManager()
        .getPlugin(name)
        ?.takeIf { it.isEnabled }
        ?.pluginMeta
        ?.version