package com.github.mrjimin.betonquestaddon.compatibility.customnameplates

import com.github.mrjimin.betonquestaddon.compatibility.ICompatibility
import com.github.mrjimin.betonquestaddon.compatibility.customnameplates.action.NpApplyActionFactory
import com.github.mrjimin.betonquestaddon.compatibility.customnameplates.condition.NpEquippedConditionFactory
import com.github.mrjimin.betonquestaddon.compatibility.customnameplates.condition.NpHasConditionFactory
import org.betonquest.betonquest.api.BetonQuestApi

class CustomNameplatesIntegrator : ICompatibility {
    override fun hook(api: BetonQuestApi) {
        val questRegistries = api.questRegistries

        questRegistries.action().apply {
            register("customNameplatesApplyNameplate", NpApplyActionFactory())
        }

        questRegistries.condition().apply {
            register("customNameplatesHasNameplate", NpHasConditionFactory(NpCheckType.NAMEPLATE))
            register("customNameplatesEquippedNameplate", NpEquippedConditionFactory(NpCheckType.NAMEPLATE))
            register("customNameplatesHasBubble", NpHasConditionFactory(NpCheckType.BUBBLE))
            register("customNameplatesEquippedBubble", NpEquippedConditionFactory(NpCheckType.BUBBLE))
        }
    }
}