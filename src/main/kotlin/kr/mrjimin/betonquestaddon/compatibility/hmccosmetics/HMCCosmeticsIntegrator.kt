package kr.mrjimin.betonquestaddon.compatibility.hmccosmetics

import kr.mrjimin.betonquestaddon.compatibility.ICompatibility
import kr.mrjimin.betonquestaddon.compatibility.hmccosmetics.action.HMCApplyActionFactory
import kr.mrjimin.betonquestaddon.compatibility.hmccosmetics.condition.HMCInWardrobeConditionFactory
import kr.mrjimin.betonquestaddon.compatibility.hmccosmetics.objective.HMCCosmeticEquipObjectiveFactory
import org.betonquest.betonquest.api.BetonQuestApi

class HMCCosmeticsIntegrator : ICompatibility {

    override fun hook(api: BetonQuestApi) {
//        Bukkit.getServer().pluginManager.registerEvents(this, plugin)
        api.actions().registry().apply {
            register("hmcCosmeticsApplyCosmetic", HMCApplyActionFactory())
        }

        api.conditions().registry().apply {
            register("hmcCosmeticsInWardrobe", HMCInWardrobeConditionFactory())
        }

        api.objectives().registry().apply {
            register("hmcCosmeticsEquipCosmetic", HMCCosmeticEquipObjectiveFactory())
        }
    }

//    @EventHandler(ignoreCancelled = true)
//    fun onCosmeticReload(event: HMCCosmeticSetupEvent) {
//        HMCCosmeticsProvider.clearCache()
//    }
}