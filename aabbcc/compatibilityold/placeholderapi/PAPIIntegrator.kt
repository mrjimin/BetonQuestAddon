package com.github.mrjimin.betonquestaddon.compatibilityold.placeholderapi

import com.github.mrjimin.betonquestaddon.compatibilityold.BQAddonIntegrator
import com.github.mrjimin.betonquestaddon.compatibilityold.placeholderapi.conditions.PAPIConditionFactory

object PAPIIntegrator : BQAddonIntegrator() {
    override fun hook() {
        condition.register("PAPI", PAPIConditionFactory(data))
    }

}