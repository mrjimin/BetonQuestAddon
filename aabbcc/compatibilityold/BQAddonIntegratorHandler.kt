//package com.github.mrjimin.betonquestaddon.compatibilityold
//
//import com.github.mrjimin.betonquestaddon.compatibilityold.advancedenchantments.AEIntegrator
//import com.github.mrjimin.betonquestaddon.compatibilityold.coinsengine.CoinsEngineIntegrator
//import com.github.mrjimin.betonquestaddon.compatibilityold.craftengine.CraftEngineIntegrator
//import com.github.mrjimin.betonquestaddon.compatibilityold.customcrops.CustomCropsIntegrator
//import com.github.mrjimin.betonquestaddon.compatibilityold.headdatabase.HDBIntegrator
//import com.github.mrjimin.betonquestaddon.compatibilityold.itemsadder.ItemsAdderIntegrator
//import com.github.mrjimin.betonquestaddon.compatibilityold.lands.LandsIntegrator
//import com.github.mrjimin.betonquestaddon.compatibilityold.nexo.NexoIntegrator
//import com.github.mrjimin.betonquestaddon.compatibilityold.placeholderapi.PAPIIntegrator
//import com.github.mrjimin.betonquestaddon.compatibilityold.worldguard.WorldGuardIntegrator
//import com.github.mrjimin.betonquestaddon.util.server.checkPlugin
//import org.betonquest.betonquest.api.logger.BetonQuestLogger
//
//class BQAddonIntegratorHandler(
//    val logger: BetonQuestLogger
////    val data: PrimaryServerThreadData,
////    val pluginMessage: PluginMessage,
////    val variableProcessor: VariableProcessor,
////    val questRegistries: QuestTypeRegistries
//) {
//
//    companion object {
//        private val hookedPlugins = mutableSetOf<String>()
//
//        fun addHookedPlugin(name: String) {
//            hookedPlugins.add(name)
//        }
//
//        fun getHookedPlugins(): List<String> = hookedPlugins.toList()
//        fun clearHookedPlugins() = hookedPlugins.clear()
//    }
//
//    private val integrators = mutableMapOf<String, BQAddonIntegrator>()
//
//    init {
//        registerCompatiblePlugins()
//        hookAll()
//    }
//
//    private fun register(pluginName: String, integrator: BQAddonIntegrator) {
//        integrators[pluginName] = integrator
//    }
//
//    private fun hookAll() {
//        integrators.forEach { (pluginName, integrator) ->
//            if (!pluginName.checkPlugin()) {
//                logger.debug("Plugin $pluginName not found, skipping integration.")
//                return@forEach
//            }
//            runCatching {
//                integrator.hook()
//                addHookedPlugin(pluginName)
//                logger.info("Successfully hooked into $pluginName.")
//            }.onFailure { ex ->
//                logger.warn("Failed to hook into $pluginName: ${ex.message}", ex)
//            }
//        }
//    }
//
//    private fun registerCompatiblePlugins() {
//        register("CoinsEngine", CoinsEngineIntegrator)
//        register("Nexo", NexoIntegrator)
//        register("ItemsAdder", ItemsAdderIntegrator)
//        register("CraftEngine", CraftEngineIntegrator)
//        register("CustomCrops", CustomCropsIntegrator)
//        register("HeadDatabase", HDBIntegrator)
//        register("AdvancedEnchantments", AEIntegrator)
//        register("PlaceholderAPI", PAPIIntegrator)
//        register("Lands", LandsIntegrator)
//        register("WorldGuard", WorldGuardIntegrator)
//    }
//
//}
