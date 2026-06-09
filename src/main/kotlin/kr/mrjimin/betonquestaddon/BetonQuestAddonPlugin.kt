package kr.mrjimin.betonquestaddon

import kr.mrjimin.betonquestaddon.command.CommandsHandler
import kr.mrjimin.betonquestaddon.command.register
import kr.mrjimin.betonquestaddon.compatibility.CompatibilityManager
import kr.mrjimin.betonquestaddon.config.ConfigsManager
import kr.mrjimin.betonquestaddon.util.Logger
import kr.mrjimin.betonquestaddon.util.UpdateChecker
import kr.mrjimin.betonquestaddon.util.getPluginVersion
import org.betonquest.betonquest.api.BetonQuestApi
import org.betonquest.betonquest.api.BetonQuestApiService
import org.bstats.bukkit.Metrics
import org.bstats.charts.AdvancedPie
import org.bukkit.plugin.java.JavaPlugin

class BetonQuestAddonPlugin : JavaPlugin() {

    lateinit var compatManager: CompatibilityManager
        private set
    lateinit var configsManager: ConfigsManager
        private set

    override fun onEnable() {
        val betonQuestApi = loadBetonQuestApi() ?: return

        printEnabledMessage()

        initConfigs()
        initCompatibility(betonQuestApi)
        registerCommands()

        initMetrics()
        checkForUpdates()
    }

    private fun loadBetonQuestApi(): BetonQuestApi? {
        val api = server.servicesManager
            .load(BetonQuestApiService::class.java)
            ?.api(this)

        if (api == null) {
            Logger.error("BetonQuest is not available.")
            server.pluginManager.disablePlugin(this)
        }

        return api
    }

    private fun initConfigs() {
        configsManager = ConfigsManager(this)
        configsManager.init()
    }

    private fun initCompatibility(api: BetonQuestApi) {
        compatManager = CompatibilityManager(this, api)
        compatManager.registerCompatiblePlugins()
    }

    private fun registerCommands() {
        CommandsHandler(this).register("betonquestaddon")
    }

    private fun initMetrics() {
        val metrics = Metrics(this, 26421)

        metrics.addCustomChart(
            AdvancedPie("hooks") {
                compatManager.getHookedPluginNames()
                    .associateWith { 1 }
                    .toMutableMap()
                    .ifEmpty { mutableMapOf("None" to 1) }
            }
        )
    }

    private fun checkForUpdates() {
        if (configsManager.updateChecker()) {
            UpdateChecker("XvDcVrRl", this).checkForUpdates()
        }
    }

    private fun printEnabledMessage() {
        Logger.info("<color:#707070>========================================</color>")
        Logger.info("BetonQuestAddon <color:#00d2ff>v${pluginMeta.version}</color>")
        Logger.info("BetonQuest <dark_gray>v${getPluginVersion("BetonQuest")}</dark_gray>")
        Logger.info("Server <color:#e3a814>${server.name}</color> <gray>(MC ${server.minecraftVersion})</gray>")
        Logger.info("Status: <color:#50fa7b>Successfully enabled</color>")
        Logger.info("<color:#707070>========================================</color>")
    }
}