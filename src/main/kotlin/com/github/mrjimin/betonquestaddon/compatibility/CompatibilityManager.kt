package com.github.mrjimin.betonquestaddon.compatibility

import com.github.mrjimin.betonquestaddon.betonquest.BetonQuestIntegrator
import com.github.mrjimin.betonquestaddon.compatibility.craftengine.CraftEngineIntegrator
import com.github.mrjimin.betonquestaddon.compatibility.customcrops.CustomCropsIntegrator
import com.github.mrjimin.betonquestaddon.compatibility.customfishing.CustomFishingIntegrator
import com.github.mrjimin.betonquestaddon.compatibility.customnameplates.CustomNameplatesIntegrator
import com.github.mrjimin.betonquestaddon.compatibility.hmccosmetics.HMCCosmeticsIntegrator
import com.github.mrjimin.betonquestaddon.compatibility.itemsadder.ItemsAdderIntegrator
import com.github.mrjimin.betonquestaddon.compatibility.nexo.NexoIntegrator
import com.github.mrjimin.betonquestaddon.compatibility.typewriter.TypeWriterIntegrator
import com.github.mrjimin.betonquestaddon.compatibility.worldguard.WorldGuardIntegrator
import com.github.mrjimin.betonquestaddon.util.Logger
import org.betonquest.betonquest.api.BetonQuestApi
import org.bukkit.Bukkit
import org.bukkit.plugin.java.JavaPlugin

class CompatibilityManager(
    private val api: BetonQuestApi,
    private val plugin: JavaPlugin
) {

    private val integrators = mutableMapOf<String, ICompatibility>()

    fun registerCompatiblePlugins() {
        registerBetonQuestIntegrator()
        register("Nexo") { NexoIntegrator() }
        register("CraftEngine") { CraftEngineIntegrator() }
        register("ItemsAdder") { ItemsAdderIntegrator() }
        register("CustomCrops") { CustomCropsIntegrator() }
        register("CustomFishing") { CustomFishingIntegrator() }
        register("CustomNameplates") { CustomNameplatesIntegrator() }
        register("HMCCosmetics") { HMCCosmeticsIntegrator(plugin) }
        register("TypeWriter") { TypeWriterIntegrator() }
        register("WorldGuard") { WorldGuardIntegrator() }
    }

    private fun register(name: String, factory: () -> ICompatibility) {
        if (name in integrators) return
        if (!plugin.config.getBoolean("hook.$name", true)) return

        val registerPlugin = Bukkit.getPluginManager().getPlugin(name)?.takeIf { it.isEnabled } ?: return
        integrators[name] = factory().apply { hook(api) }

        val version = registerPlugin.pluginMeta.version
        Logger.info("<green>Successfully hooked into <gray>$name <dark_gray>v$version")
    }

    private fun registerBetonQuestIntegrator() {
        BetonQuestIntegrator(api).hook()
    }
}
