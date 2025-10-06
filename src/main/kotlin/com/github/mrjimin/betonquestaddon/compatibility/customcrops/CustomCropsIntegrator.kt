package com.github.mrjimin.betonquestaddon.compatibility.customcrops

import com.github.mrjimin.betonquestaddon.compatibility.BQAddonIntegrator
import com.github.mrjimin.betonquestaddon.compatibility.customcrops.objectives.CCropsHarvestObjectiveFactory
import com.github.mrjimin.betonquestaddon.compatibility.customcrops.objectives.CCropsPlantObjectiveFactory

object CustomCropsIntegrator : BQAddonIntegrator() {
    override fun hook() {
        objective.apply {
            register("cCropsHarvest", CCropsHarvestObjectiveFactory)
            register("cCropsPlant", CCropsPlantObjectiveFactory)
        }
    }

}