package com.github.mrjimin.betonquestaddon

import com.github.mrjimin.betonquestaddon.betonquest.BetonQuestAddon
import com.github.mrjimin.betonquestaddon.command.CommandsManger
import com.github.mrjimin.betonquestaddon.config.ConfigsManager
import com.github.mrjimin.betonquestaddon.lib.bstats.Metrics
import com.github.mrjimin.betonquestaddon.spigot.UpdateChecker
import dev.jorel.commandapi.CommandAPI
import dev.jorel.commandapi.CommandAPIBukkitConfig
import org.bukkit.plugin.java.JavaPlugin

@Suppress("UnstableApiUsage")
class BetonQuestAddonPlugin : JavaPlugin() {

    companion object {
        lateinit var instance: BetonQuestAddonPlugin
            private set
//        lateinit var advancement: UltimateAdvancementAPI
//            private set
    }


    override fun onLoad() {
        CommandAPI.onLoad(CommandAPIBukkitConfig(this).verboseOutput(true))
    }

    override fun onEnable() {
        instance = this
        // advancement = UltimateAdvancementAPI.getInstance(this)
        CommandAPI.onEnable()

        Metrics(this, 26421)
        BetonQuestAddon.initialize()
        UpdateChecker.checkForUpdates(this, 120813)

        ConfigsManager(this).reload()
        CommandsManger(this).loadsCommands()

        logger.info("BetonQuestAddon v${pluginMeta.version} successfully enabled.")

        // TestPluginInit(this)
    }

    override fun onDisable() {
        CommandAPI.onDisable()
    }
}