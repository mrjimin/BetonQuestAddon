package com.github.mrjimin.betonquestaddon.compatibility.customnameplates

import com.github.mrjimin.betonquestaddon.compatibility.ICompatibility
import com.github.mrjimin.betonquestaddon.compatibility.customnameplates.condition.NpEquippedConditionFactory
import com.github.mrjimin.betonquestaddon.compatibility.customnameplates.condition.NpHasConditionFactory
import org.betonquest.betonquest.api.BetonQuestApi

class CustomNameplatesIntegrator : ICompatibility {
    override fun hook(api: BetonQuestApi) {
        val questRegistries = api.questRegistries
        val loggerFactory = api.loggerFactory

        questRegistries.condition().apply {
            register("customNameplatesHasNameplate", NpHasConditionFactory(NpCheckType.NAMEPLATE, loggerFactory))
            register("customNameplatesEquippedNameplate", NpEquippedConditionFactory(NpCheckType.NAMEPLATE, loggerFactory))
            register("customNameplatesHasBubble", NpHasConditionFactory(NpCheckType.BUBBLE, loggerFactory))
            register("customNameplatesEquippedBubble", NpEquippedConditionFactory(NpCheckType.BUBBLE, loggerFactory))
        }
    }
}