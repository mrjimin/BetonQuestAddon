package com.github.mrjimin.betonquestaddon.compatibility.hmccosmetics

import com.github.mrjimin.betonquestaddon.compatibility.ICompatibility
import com.github.mrjimin.betonquestaddon.compatibility.hmccosmetics.action.HMCApplyActionFactory
import com.github.mrjimin.betonquestaddon.compatibility.hmccosmetics.condition.HMCInWardrobeConditionFactory
import com.hibiscusmc.hmccosmetics.api.events.HMCCosmeticSetupEvent
import org.betonquest.betonquest.api.BetonQuestApi
import org.bukkit.Bukkit
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.plugin.java.JavaPlugin

class HMCCosmeticsIntegrator(private val plugin: JavaPlugin) : ICompatibility, Listener {

    override fun hook(api: BetonQuestApi) {
        Bukkit.getServer().pluginManager.registerEvents(this, plugin)
        val questRegistries = api.questRegistries

        questRegistries.action().apply {
            register("hmcCosmeticsApplyCosmetic", HMCApplyActionFactory())
        }

        questRegistries.condition().apply {
            register("hmcCosmeticsInWardrobe", HMCInWardrobeConditionFactory())
        }
    }

    @EventHandler(ignoreCancelled = true)
    fun onCosmeticReload(event: HMCCosmeticSetupEvent) {
        HMCCosmeticsProvider.clearCache()
    }
}