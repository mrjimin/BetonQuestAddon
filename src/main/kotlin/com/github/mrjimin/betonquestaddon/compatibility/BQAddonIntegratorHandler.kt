package com.github.mrjimin.betonquestaddon.compatibility

import com.github.mrjimin.betonquestaddon.compatibility.advancedenchantments.AEIntegrator
import com.github.mrjimin.betonquestaddon.compatibility.coinsengine.CoinsEngineIntegrator
import com.github.mrjimin.betonquestaddon.compatibility.craftengine.CraftEngineIntegrator
import com.github.mrjimin.betonquestaddon.compatibility.headdatabase.HDBIntegrator
import com.github.mrjimin.betonquestaddon.compatibility.itemsadder.ItemsAdderIntegrator
import com.github.mrjimin.betonquestaddon.compatibility.nexo.NexoIntegrator
import com.github.mrjimin.betonquestaddon.util.checkPlugin
import org.betonquest.betonquest.api.logger.BetonQuestLogger
import org.betonquest.betonquest.config.PluginMessage
import org.betonquest.betonquest.kernel.processor.quest.VariableProcessor
import org.betonquest.betonquest.kernel.registry.quest.QuestTypeRegistries
import org.betonquest.betonquest.quest.PrimaryServerThreadData

class BQAddonIntegratorHandler(
    val loggerFactory: BetonQuestLogger,
    val data: PrimaryServerThreadData,
    val pluginMessage: PluginMessage,
    val variableProcessor: VariableProcessor,
    val questRegistries: QuestTypeRegistries
) {

    private val integrators = mutableMapOf<String, () -> BQAddonIntegrator>()

    init {
        registerCompatiblePlugins()
        hookAll()
    }

    private fun register(pluginName: String, integratorSupplier: () -> BQAddonIntegrator) {
        integrators[pluginName] = integratorSupplier
    }

    private fun hookAll() {
        integrators.forEach { (pluginName, supplier) ->
            if (!pluginName.checkPlugin()) {
                loggerFactory.debug("Plugin $pluginName not found, skipping integration.")
                return@forEach
            }
            try {
                supplier().hook()
                loggerFactory.info("Successfully hooked into $pluginName.")
            } catch (ex: Exception) {
                loggerFactory.warn("Failed to hook into $pluginName: ${ex.message}", ex)
            }
        }
    }

    private fun registerCompatiblePlugins() {
        register("CoinsEngine") { CoinsEngineIntegrator }
        register("Nexo") { NexoIntegrator }
        register("ItemsAdder") { ItemsAdderIntegrator }
        register("CraftEngine") { CraftEngineIntegrator }
        register("HeadDatabase") { HDBIntegrator }
        register("AdvancedEnchantments") { AEIntegrator }
    }
}
