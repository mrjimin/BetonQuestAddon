package kr.mrjimin.betonquestaddon.compatibility.customnameplates

import kr.mrjimin.betonquestaddon.compatibility.ICompatibility
import kr.mrjimin.betonquestaddon.compatibility.customnameplates.action.NpApplyActionFactory
import kr.mrjimin.betonquestaddon.compatibility.customnameplates.condition.NpEquippedConditionFactory
import kr.mrjimin.betonquestaddon.compatibility.customnameplates.condition.NpHasConditionFactory
import org.betonquest.betonquest.api.BetonQuestApi

class CustomNameplatesIntegrator : ICompatibility {
    override fun hook(api: BetonQuestApi) {
        api.actions().registry().apply {
            register("customNameplatesApplyNameplate", NpApplyActionFactory())
        }

        api.conditions().registry().apply {
            register("customNameplatesHasNameplate", NpHasConditionFactory(NpCheckType.NAMEPLATE))
            register("customNameplatesEquippedNameplate", NpEquippedConditionFactory(NpCheckType.NAMEPLATE))
            register("customNameplatesHasBubble", NpHasConditionFactory(NpCheckType.BUBBLE))
            register("customNameplatesEquippedBubble", NpEquippedConditionFactory(NpCheckType.BUBBLE))
        }
    }
}