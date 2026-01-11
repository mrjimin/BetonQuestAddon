package com.github.mrjimin.betonquestaddon.compatibility.customfishing

import com.github.mrjimin.betonquestaddon.compatibility.ICompatibility
import org.betonquest.betonquest.api.BetonQuestApi

class CustomFishingIntegrator : ICompatibility {
    override fun hook(api: BetonQuestApi) {
        val questRegistries = api.questRegistries

        questRegistries.objective().apply {
        }
    }
}