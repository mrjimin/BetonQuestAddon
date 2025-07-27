package com.github.mrjimin.betonquestaddon.compatibility.advancedenchantments

import com.github.mrjimin.betonquestaddon.compatibility.BQAddonIntegrator
import com.github.mrjimin.betonquestaddon.compatibility.advancedenchantments.objectives.AEAlchemistTradeObjectiveFactory
import com.github.mrjimin.betonquestaddon.compatibility.advancedenchantments.objectives.AEBookOpenObjectiveFactory
import com.github.mrjimin.betonquestaddon.compatibility.advancedenchantments.objectives.AETinkererTradeObjectiveFactory

object AEIntegrator : BQAddonIntegrator() {
    override fun hook() {
        objective.apply {
            register("aeBookOpen", AEBookOpenObjectiveFactory(loggerFactory))
            register("aeTinkererTrade", AETinkererTradeObjectiveFactory(loggerFactory))
            register("aeAlchemistTrade", AEAlchemistTradeObjectiveFactory(loggerFactory))
        }
    }
}