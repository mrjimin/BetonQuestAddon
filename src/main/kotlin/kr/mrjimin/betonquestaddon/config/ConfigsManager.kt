package kr.mrjimin.betonquestaddon.config

import org.bukkit.configuration.file.FileConfiguration
import org.bukkit.plugin.java.JavaPlugin

class ConfigsManager(private val plugin: JavaPlugin) {

    lateinit var dialog: DialogConfig
        private set

//    lateinit var hooks: HookConfig
//        private set

    private var config: FileConfiguration = plugin.config

    fun load() {
        plugin.saveDefaultConfig()
        plugin.reloadConfig()
        config = plugin.config

        dialog = DialogConfig(config)
        // hooks = HookConfig(config)
    }

    fun reload() = load()

    fun updateChecker(): Boolean =
        config.getBoolean("setting.update-checker", true)
}