package kr.mrjimin.betonquestaddon.compatibility.hmccosmetics

import com.hibiscusmc.hmccosmetics.api.events.HMCCosmeticSetupEvent
import kr.mrjimin.betonquestaddon.compatibility.ICompatibility
import kr.mrjimin.betonquestaddon.compatibility.hmccosmetics.action.HMCApplyActionFactory
import kr.mrjimin.betonquestaddon.compatibility.hmccosmetics.condition.HMCInWardrobeConditionFactory
import org.betonquest.betonquest.api.BetonQuestApi
import org.bukkit.Bukkit
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.plugin.java.JavaPlugin

class HMCCosmeticsIntegrator(private val plugin: JavaPlugin) : ICompatibility, Listener {

    override fun hook(api: BetonQuestApi) {
        Bukkit.getServer().pluginManager.registerEvents(this, plugin)
        api.actions().registry().apply {
            register("hmcCosmeticsApplyCosmetic", HMCApplyActionFactory())
        }

        api.conditions().registry().apply {
            register("hmcCosmeticsInWardrobe", HMCInWardrobeConditionFactory())
        }
    }

    @EventHandler(ignoreCancelled = true)
    fun onCosmeticReload(event: HMCCosmeticSetupEvent) {
        HMCCosmeticsProvider.clearCache()
    }
}