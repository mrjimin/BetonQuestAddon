package com.github.mrjimin.betonquestaddon.compatibility.coinsengine

import com.github.mrjimin.betonquestaddon.compatibility.ICompatibility
import com.github.mrjimin.betonquestaddon.compatibility.coinsengine.action.CoinsEngineActionFactory
import com.github.mrjimin.betonquestaddon.compatibility.coinsengine.condition.CoinsEngineConditionFactory
import org.betonquest.betonquest.api.BetonQuestApi

class CoinsEngineIntegrator : ICompatibility {
    override fun hook(api: BetonQuestApi) {
        val questRegistries = api.questRegistries

        questRegistries.action().apply {
            register("coinsEngine", CoinsEngineActionFactory())
        }

        questRegistries.condition().apply {
            register("coinsEngine", CoinsEngineConditionFactory())
        }
    }
}