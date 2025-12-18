package com.github.mrjimin.betonquestaddon

import com.github.mrjimin.betonquestaddon.compatibility.CompatibilityManager
import com.github.mrjimin.betonquestaddon.shadow.bstats.Metrics
import com.github.mrjimin.betonquestaddon.spigot.UpdateChecker
import org.betonquest.betonquest.BetonQuest
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
        CompatibilityManager(BetonQuest.getInstance()).registerCompatiblePlugins()

        logger.info("BetonQuestAddon v${pluginMeta.version} successfully enabled.")

        // TestPluginInit(this)
        UpdateChecker.checkForUpdates(this, 120813)
    }

}