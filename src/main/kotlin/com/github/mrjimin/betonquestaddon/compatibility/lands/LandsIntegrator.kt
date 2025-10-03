package com.github.mrjimin.betonquestaddon.compatibility.lands

import com.github.mrjimin.betonquestaddon.compatibility.BQAddonIntegrator
import com.github.mrjimin.betonquestaddon.compatibility.lands.objectives.LandCreateObjectiveFactory
import com.github.mrjimin.betonquestaddon.compatibility.lands.objectives.LandDeleteObjectiveFactory

object LandsIntegrator : BQAddonIntegrator() {
    override fun hook() {
        objective.apply {
            register("landCreate", LandCreateObjectiveFactory)
            register("landDelete", LandDeleteObjectiveFactory)
        }
    }
}