package com.github.mrjimin.betonquestaddon.compatibilityold.customcrops

import com.github.mrjimin.betonquestaddon.compatibilityold.BQAddonIntegrator
import com.github.mrjimin.betonquestaddon.compatibilityold.customcrops.objectives.CCropsHarvestObjectiveFactory
import com.github.mrjimin.betonquestaddon.compatibilityold.customcrops.objectives.CCropsPlantObjectiveFactory

object CustomCropsIntegrator : BQAddonIntegrator() {
    override fun hook() {
        objective.apply {
            register("cCropsHarvest", CCropsHarvestObjectiveFactory)
            register("cCropsPlant", CCropsPlantObjectiveFactory)
        }
    }

}