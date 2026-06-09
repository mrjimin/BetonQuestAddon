package kr.mrjimin.betonquestaddon.config

import org.bukkit.configuration.file.FileConfiguration

class HookConfig(private val config: FileConfiguration) {

    val enabledHooks: List<String>
        get() = config.getConfigurationSection("hook")?.getKeys(false)
            ?.filter { config.getBoolean("hook.$it") }
            ?: emptyList()
}