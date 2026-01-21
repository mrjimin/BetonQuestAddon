package com.github.mrjimin.betonquestaddon

import com.github.mrjimin.betonquestaddon.compatibility.CompatibilityManager
import com.github.mrjimin.betonquestaddon.config.ConfigsManager
import com.github.mrjimin.betonquestaddon.util.Logger
import org.betonquest.betonquest.BetonQuest
import org.bstats.bukkit.Metrics
import org.bukkit.Bukkit
import org.bukkit.plugin.java.JavaPlugin

class BetonQuestAddonPlugin : JavaPlugin() {

//    companion object {
//        lateinit var INSTANCE: BetonQuestAddonPlugin
//            private set
//    }
//
//    override fun onLoad() {
//        INSTANCE = this
//    }

    override fun onEnable() {
        Metrics(this, 26421)
        ConfigsManager(this).load()
        enabledMessage()
        CompatibilityManager(BetonQuest.getInstance(), this).registerCompatiblePlugins()
        // UpdateChecker.checkForUpdates(this, 120813)
    }

    private fun enabledMessage() {
        Logger.info("<color:#707070>========================================</color>")
        Logger.info("BetonQuestAddon <color:#00d2ff>v${pluginMeta.version}</color>")
        Logger.info("${Bukkit.getServer().name} <color:#e3a814>v${Bukkit.getServer().minecraftVersion}</color>")
        Logger.info("Status: <color:#50fa7b>Successfully enabled</color>")
        Logger.info("<color:#707070>========================================</color>")
    }

}