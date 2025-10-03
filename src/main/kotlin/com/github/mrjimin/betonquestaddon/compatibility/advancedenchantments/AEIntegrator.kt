package com.github.mrjimin.betonquestaddon.compatibility.advancedenchantments

import com.github.mrjimin.betonquestaddon.compatibility.BQAddonIntegrator
import com.github.mrjimin.betonquestaddon.compatibility.advancedenchantments.conditions.AEHasCEConditionFactory
import com.github.mrjimin.betonquestaddon.compatibility.advancedenchantments.events.AEEnchantmentFactory
import com.github.mrjimin.betonquestaddon.compatibility.advancedenchantments.objectives.AEAlchemistTradeObjectiveFactory
import com.github.mrjimin.betonquestaddon.compatibility.advancedenchantments.objectives.AEBookOpenObjectiveFactory
import com.github.mrjimin.betonquestaddon.compatibility.advancedenchantments.objectives.AETinkererTradeObjectiveFactory

object AEIntegrator : BQAddonIntegrator() {
    override fun hook() {
        condition.apply {
            register("aeEnchantTotal", AEHasCEConditionFactory(data))
        }
        event.apply {
            register("aeEnchant", AEEnchantmentFactory(data))
        }
        objective.apply {
            register("aeBookOpen", AEBookOpenObjectiveFactory)
            register("aeTinkererTrade", AETinkererTradeObjectiveFactory)
            register("aeAlchemistTrade", AEAlchemistTradeObjectiveFactory)
        }
    }
}