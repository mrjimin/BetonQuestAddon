package kr.mrjimin.betonquestaddon.compatibility.cosmeticscore

import kr.mrjimin.betonquestaddon.compatibility.ICompatibility
import kr.mrjimin.betonquestaddon.compatibility.cosmeticscore.action.CCApplyActionFactory
import kr.mrjimin.betonquestaddon.compatibility.cosmeticscore.condition.CCInWardrobeConditionFactory
import org.betonquest.betonquest.api.BetonQuestApi

class CosmeticsCoreIntegrator : ICompatibility {

    override fun hook(api: BetonQuestApi) {
        val questRegistries = api.questRegistries

        questRegistries.action().apply {
            register("cosmeticsCoreApplyCosmetic", CCApplyActionFactory())
        }

        questRegistries.condition().apply {
            register("cosmeticsCoreInWardrobe", CCInWardrobeConditionFactory())
        }
    }

    // CosmeticsCore Reload Event 없음

}