package kr.mrjimin.betonquestaddon.compatibility.customcrops

import kr.mrjimin.betonquestaddon.compatibility.ICompatibility
import kr.mrjimin.betonquestaddon.compatibility.customcrops.action.CustomCropsSetSeasonActionFactory
import kr.mrjimin.betonquestaddon.compatibility.customcrops.condition.CustomCropsSeasonConditionFactory
import kr.mrjimin.betonquestaddon.compatibility.customcrops.objective.common.ScarecrowObjectiveFactory
import kr.mrjimin.betonquestaddon.compatibility.customcrops.objective.crop.CropObjectiveFactory
import kr.mrjimin.betonquestaddon.compatibility.customcrops.objective.fertilizer.FertilizerUseObjectiveFactory
import kr.mrjimin.betonquestaddon.compatibility.customcrops.objective.pot.PotObjectiveFactory
import kr.mrjimin.betonquestaddon.compatibility.customcrops.objective.sprinkler.SprinklerObjectiveFactory
import kr.mrjimin.betonquestaddon.compatibility.customcrops.objective.wateringcan.CanFillObjectiveFactory
import kr.mrjimin.betonquestaddon.compatibility.customcrops.objective.wateringcan.CanPotObjectiveFactory
import kr.mrjimin.betonquestaddon.compatibility.customcrops.objective.wateringcan.CanSprinklerObjectiveFactory
import kr.mrjimin.betonquestaddon.config.NotifyMessage
import kr.mrjimin.betonquestaddon.util.action.Action
import org.betonquest.betonquest.api.BetonQuestApi

class CustomCropsIntegrator : ICompatibility {
    override fun hook(api: BetonQuestApi) {
        api.conditions().registry().apply {
            registerCombined("customCropsSeason", CustomCropsSeasonConditionFactory())
        }

        api.actions().registry().apply {
            register("customCropsSetSeason", CustomCropsSetSeasonActionFactory())
        }

        api.objectives().registry().apply {
            register("customCropsCropPlant", CropObjectiveFactory(Action.PLACE, NotifyMessage.CUSTOM_CROPS_CROP_PLANT))
            register("customCropsCropHarvest", CropObjectiveFactory(Action.BREAK, NotifyMessage.CUSTOM_CROPS_CROP_HARVEST))

            register("customCropsPotPlace", PotObjectiveFactory(Action.PLACE, NotifyMessage.CUSTOM_CROPS_POT_PLACE))
            register("customCropsPotBreak", PotObjectiveFactory(Action.BREAK, NotifyMessage.CUSTOM_CROPS_POT_BREAK))

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