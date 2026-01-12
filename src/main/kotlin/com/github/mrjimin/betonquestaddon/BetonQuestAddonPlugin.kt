package com.github.mrjimin.betonquestaddon

import com.github.mrjimin.betonquestaddon.compatibility.CompatibilityManager
import com.github.mrjimin.betonquestaddon.spigot.UpdateChecker
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
        saveDefaultConfig()

        logger.info("========================================")
        logger.info("BetonQuestAddon v${pluginMeta.version}")
        logger.info("Status: Successfully enabled")
        logger.info("========================================")

        CompatibilityManager(BetonQuest.getInstance(), config).registerCompatiblePlugins()
        // UpdateChecker.checkForUpdates(this, 120813)
    }

}