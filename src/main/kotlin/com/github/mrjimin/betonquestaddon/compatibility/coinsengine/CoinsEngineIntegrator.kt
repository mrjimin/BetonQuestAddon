package com.github.mrjimin.betonquestaddon.compatibility.coinsengine

import com.github.mrjimin.betonquestaddon.compatibility.BQAddonIntegrator
import com.github.mrjimin.betonquestaddon.compatibility.coinsengine.conditions.CoinsConditionFactory
import com.github.mrjimin.betonquestaddon.compatibility.coinsengine.events.CoinsEventFactory

object CoinsEngineIntegrator : BQAddonIntegrator() {
    override fun hook() {
        condition.register("coins", CoinsConditionFactory(data))
        event.register("coins", CoinsEventFactory(logger, data, pluginMessage, variableProcessor))
    }
}
