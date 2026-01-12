package com.github.mrjimin.betonquestaddon

import com.github.mrjimin.betonquestaddon.compatibility.CompatibilityManager
import com.github.mrjimin.betonquestaddon.config.ConfigManager
import com.github.mrjimin.betonquestaddon.util.enabledMessage
import org.betonquest.betonquest.BetonQuest
import org.bstats.bukkit.Metrics
import org.bukkit.plugin.java.JavaPlugin

class BetonQuestAddonPlugin : JavaPlugin() {

    companion object {
        lateinit var INSTANCE: BetonQuestAddonPlugin
            private set
    }

    override fun onLoad() {
        INSTANCE = this
    }

    override fun onEnable() {
        Metrics(this, 26421)
        enabledMessage()
        ConfigManager(this).load()
        CompatibilityManager(BetonQuest.getInstance(), this).registerCompatiblePlugins()
        // UpdateChecker.checkForUpdates(this, 120813)
    }

}