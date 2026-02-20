package kr.mrjimin.betonquestaddon

import kr.mrjimin.betonquestaddon.compatibility.CompatibilityManager
import kr.mrjimin.betonquestaddon.config.ConfigsManager
import kr.mrjimin.betonquestaddon.util.Logger
import kr.mrjimin.betonquestaddon.util.UpdateChecker
import org.betonquest.betonquest.BetonQuest
import org.betonquest.betonquest.api.BetonQuestApi
import org.bstats.bukkit.Metrics
import org.bukkit.Bukkit
import org.bukkit.plugin.java.JavaPlugin

class BetonQuestAddonPlugin : JavaPlugin() {

    override fun onEnable() {
        Metrics(this, 26421)
        ConfigsManager(this).load()
        enabledMessage()
        CompatibilityManager(BetonQuest.getInstance().betonQuestApi, this).registerCompatiblePlugins()
        if (ConfigsManager.enabledUpdateChecker()) UpdateChecker(this).checkForUpdates()
    }

    private fun enabledMessage() {
        Logger.info("<color:#707070>========================================</color>")
        Logger.info("BetonQuestAddon <color:#00d2ff>v${pluginMeta.version}</color>")
        Logger.info("${Bukkit.getServer().name} <color:#e3a814>v${Bukkit.getServer().minecraftVersion}</color>")
        Logger.info("Status: <color:#50fa7b>Successfully enabled</color>")
        Logger.info("<color:#707070>========================================</color>")
    }

}