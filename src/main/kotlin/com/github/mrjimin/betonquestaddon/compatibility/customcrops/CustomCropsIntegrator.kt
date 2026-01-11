package com.github.mrjimin.betonquestaddon.compatibility.customcrops

import com.github.mrjimin.betonquestaddon.compatibility.ICompatibility
import com.github.mrjimin.betonquestaddon.compatibility.customcrops.objective.common.ScarecrowObjectiveFactory
import com.github.mrjimin.betonquestaddon.compatibility.customcrops.objective.crop.CropObjectiveFactory
import com.github.mrjimin.betonquestaddon.compatibility.customcrops.objective.fertilizer.FertilizerUseObjectiveFactory
import com.github.mrjimin.betonquestaddon.compatibility.customcrops.objective.pot.PotObjectiveFactory
import com.github.mrjimin.betonquestaddon.compatibility.customcrops.objective.sprinkler.SprinklerObjectiveFactory
import com.github.mrjimin.betonquestaddon.compatibility.customcrops.objective.wateringcan.CanFillObjectiveFactory
import com.github.mrjimin.betonquestaddon.compatibility.customcrops.objective.wateringcan.CanPotObjectiveFactory
import com.github.mrjimin.betonquestaddon.compatibility.customcrops.objective.wateringcan.CanSprinklerObjectiveFactory
import com.github.mrjimin.betonquestaddon.config.NotifyMessage
import com.github.mrjimin.betonquestaddon.util.action.Action
import org.betonquest.betonquest.api.BetonQuestApi

class CustomCropsIntegrator : ICompatibility {
    override fun hook(api: BetonQuestApi) {
        val questRegistries = api.questRegistries

        questRegistries.objective().apply {
            register("customCropsCropPlant", CropObjectiveFactory(Action.PLACE, NotifyMessage.CUSTOM_CROPS_CROP_PLANT))
            register("customCropsCropHarvest", CropObjectiveFactory(Action.BREAK, NotifyMessage.CUSTOM_CROPS_CROP_HARVEST))

            register("customCropsPotPlace", PotObjectiveFactory(Action.PLACE, NotifyMessage.CUSTOM_CROPS_POT_PLACE))
            register("customCropsPotBream", PotObjectiveFactory(Action.BREAK, NotifyMessage.CUSTOM_CROPS_POT_BREAK))

            register("customCropsScarecrowPlace", ScarecrowObjectiveFactory(Action.PLACE, NotifyMessage.CUSTOM_CROPS_SCARECROW_PLACE))
            register("customCropsScarecrowBreak", ScarecrowObjectiveFactory(Action.BREAK, NotifyMessage.CUSTOM_CROPS_SCARECROW_BREAK))

            register("customCropsFertilizerUse", FertilizerUseObjectiveFactory())

            register("customCropsCanFill", CanFillObjectiveFactory())
            register("customCropsCanPot", CanPotObjectiveFactory())
            register("customCropsCanSprinkler", CanSprinklerObjectiveFactory())

            register("customCropsSprinklerPlace", SprinklerObjectiveFactory(Action.PLACE, NotifyMessage.CUSTOM_CROPS_SPRINKLER_PLACE))
            register("customCropsSprinklerBreak", SprinklerObjectiveFactory(Action.BREAK, NotifyMessage.CUSTOM_CROPS_SPRINKLER_BREAK))
        }
    }
}