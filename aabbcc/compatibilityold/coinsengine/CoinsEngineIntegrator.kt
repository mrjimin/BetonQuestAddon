package com.github.mrjimin.betonquestaddon.compatibilityold.coinsengine

import com.github.mrjimin.betonquestaddon.compatibilityold.BQAddonIntegrator
import com.github.mrjimin.betonquestaddon.compatibilityold.coinsengine.conditions.CoinsConditionFactory
import com.github.mrjimin.betonquestaddon.compatibilityold.coinsengine.events.CoinsEventFactory

object CoinsEngineIntegrator : BQAddonIntegrator() {
    override fun hook() {
        condition.register("coins", CoinsConditionFactory(data))
        event.register("coins", CoinsEventFactory(logger, data, pluginMessage, variableProcessor))
    }
}
