package com.github.mrjimin.betonquestaddon.compatibility.customfishing

import com.github.mrjimin.betonquestaddon.compatibility.ICompatibility
import com.github.mrjimin.betonquestaddon.compatibility.customfishing.objective.ActivateTotemObjectiveFactory
import com.github.mrjimin.betonquestaddon.compatibility.customfishing.objective.CaughtFishObjectiveFactory
import com.github.mrjimin.betonquestaddon.config.NotifyMessage
import org.betonquest.betonquest.api.BetonQuestApi

class CustomFishingIntegrator : ICompatibility {
    override fun hook(api: BetonQuestApi) {
        val questRegistries = api.questRegistries

        questRegistries.objective().apply {
            register("customFishingCaughtFish", CaughtFishObjectiveFactory(FishingCaughtType.FISH, NotifyMessage.CUSTOM_FISHING_CAUGHT_FISH))
            register("customFishingCaughtGroup", CaughtFishObjectiveFactory(FishingCaughtType.GROUP, NotifyMessage.CUSTOM_FISHING_CAUGHT_GROUP))
            register("customFishingActivateTotem", ActivateTotemObjectiveFactory())
        }
    }
}