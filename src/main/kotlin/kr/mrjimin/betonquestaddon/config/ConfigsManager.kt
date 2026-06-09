package kr.mrjimin.betonquestaddon.config

import org.bukkit.configuration.file.FileConfiguration
import org.bukkit.plugin.java.JavaPlugin

class ConfigsManager(private val plugin: JavaPlugin) {

    val config: FileConfiguration
        get() = plugin.config

    fun init() {
        plugin.saveDefaultConfig()
        reload()
    }

    fun reload() {
        plugin.reloadConfig()
    }

    fun dialog(): DialogConfig = DialogConfig(config)

    fun updateChecker(): Boolean =
        config.getBoolean("setting.update-checker", true)
}