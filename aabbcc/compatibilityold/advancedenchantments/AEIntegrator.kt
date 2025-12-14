package com.github.mrjimin.betonquestaddon.compatibilityold.advancedenchantments

import com.github.mrjimin.betonquestaddon.compatibilityold.BQAddonIntegrator
import com.github.mrjimin.betonquestaddon.compatibilityold.advancedenchantments.conditions.AEHasCEConditionFactory
import com.github.mrjimin.betonquestaddon.compatibilityold.advancedenchantments.events.AEEnchantmentFactory
import com.github.mrjimin.betonquestaddon.compatibilityold.advancedenchantments.objectives.AEAlchemistTradeObjectiveFactory
import com.github.mrjimin.betonquestaddon.compatibilityold.advancedenchantments.objectives.AEBookOpenObjectiveFactory
import com.github.mrjimin.betonquestaddon.compatibilityold.advancedenchantments.objectives.AETinkererTradeObjectiveFactory

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