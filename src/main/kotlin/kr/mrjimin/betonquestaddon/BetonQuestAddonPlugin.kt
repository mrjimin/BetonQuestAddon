package kr.mrjimin.betonquestaddon

import kr.mrjimin.betonquestaddon.compatibility.CompatibilityManager
import kr.mrjimin.betonquestaddon.config.ConfigsManager
import kr.mrjimin.betonquestaddon.util.Logger
import kr.mrjimin.betonquestaddon.util.UpdateChecker
import kr.mrjimin.betonquestaddon.util.getPluginVersion
import org.betonquest.betonquest.api.BetonQuestApiService
import org.bstats.bukkit.Metrics
import org.bukkit.plugin.java.JavaPlugin


class BetonQuestAddonPlugin : JavaPlugin() {

    override fun onEnable() {
        val betonQuestApi = server.servicesManager
            .load(BetonQuestApiService::class.java)
            ?.api(this)

        if (betonQuestApi == null) {
            Logger.error("BetonQuest is not available.")
            server.pluginManager.disablePlugin(this)
            return
        }

        Metrics(this, 26421)

        ConfigsManager(this).load()

        enabledMessage()

        CompatibilityManager(this, betonQuestApi).registerCompatiblePlugins()

        if (ConfigsManager.enabledUpdateChecker()) UpdateChecker("XvDcVrRl", this).checkForUpdates()
    }

    private fun enabledMessage() {
        Logger.info("<color:#707070>========================================</color>")
        Logger.info("BetonQuestAddon <color:#00d2ff>v${pluginMeta.version}</color>")
        Logger.info("BetonQuest <dark_gray>v${getPluginVersion("BetonQuest")}</dark_gray>")
        Logger.info("Server <color:#e3a814>${server.name}</color> <gray>(MC ${server.minecraftVersion})</gray>")
        Logger.info("Status: <color:#50fa7b>Successfully enabled</color>")
        Logger.info("<color:#707070>========================================</color>")
    }
}