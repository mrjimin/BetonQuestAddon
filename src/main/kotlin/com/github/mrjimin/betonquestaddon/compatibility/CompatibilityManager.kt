package com.github.mrjimin.betonquestaddon.compatibility

import com.github.mrjimin.betonquestaddon.compatibility.craftengine.CraftEngineIntegrator
import com.github.mrjimin.betonquestaddon.compatibility.customcrops.CustomCropsIntegrator
import com.github.mrjimin.betonquestaddon.compatibility.itemsadder.ItemsAdderIntegrator
import com.github.mrjimin.betonquestaddon.compatibility.nexo.NexoIntegrator
import com.github.mrjimin.betonquestaddon.util.toMMComponent
import org.betonquest.betonquest.api.BetonQuestApi
import org.bukkit.Bukkit
import org.bukkit.configuration.file.FileConfiguration

class CompatibilityManager(
    private val api: BetonQuestApi,
    private val config: FileConfiguration
) {

    private val integrators = mutableMapOf<String, ICompatibility>()

    fun registerCompatiblePlugins() {
        register("Nexo") { NexoIntegrator() }
        register("CraftEngine") { CraftEngineIntegrator() }
        register("ItemsAdder") { ItemsAdderIntegrator() }
        register("CustomCrops") { CustomCropsIntegrator() }
    }

    private fun register(name: String, factory: () -> ICompatibility) {
        if (name in integrators || !config.getBoolean("hook.$name", false)) return
        Bukkit.getPluginManager().getPlugin(name)?.takeIf { it.isEnabled }?.let { plugin ->
            integrators[name] = factory().apply { hook(api) }
            val version = plugin.pluginMeta.version

            Bukkit.getConsoleSender().sendMessage("[BetonQuestAddon] <green>Successfully hooked into <gray>$name ($version)".toMMComponent())
        }
    }

}
