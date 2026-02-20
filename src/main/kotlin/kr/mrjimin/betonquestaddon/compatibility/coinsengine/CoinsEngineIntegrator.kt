package kr.mrjimin.betonquestaddon.compatibility.coinsengine

import kr.mrjimin.betonquestaddon.compatibility.ICompatibility
import kr.mrjimin.betonquestaddon.compatibility.coinsengine.action.CoinsEngineActionFactory
import kr.mrjimin.betonquestaddon.compatibility.coinsengine.condition.CoinsEngineConditionFactory
import org.betonquest.betonquest.api.BetonQuestApi

class CoinsEngineIntegrator : ICompatibility {
    override fun hook(api: BetonQuestApi) {
        api.actions().registry().apply {
            register("coinsEngine", CoinsEngineActionFactory())
        }

        api.conditions().registry().apply {
            register("coinsEngine", CoinsEngineConditionFactory())
        }
    }
}