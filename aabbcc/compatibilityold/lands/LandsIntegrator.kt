package com.github.mrjimin.betonquestaddon.compatibilityold.lands

import com.github.mrjimin.betonquestaddon.compatibilityold.BQAddonIntegrator
import com.github.mrjimin.betonquestaddon.compatibilityold.lands.objectives.LandCreateObjectiveFactory
import com.github.mrjimin.betonquestaddon.compatibilityold.lands.objectives.LandDeleteObjectiveFactory

object LandsIntegrator : BQAddonIntegrator() {
    override fun hook() {
        objective.apply {
            register("landCreate", LandCreateObjectiveFactory)
            register("landDelete", LandDeleteObjectiveFactory)
        }
    }
}