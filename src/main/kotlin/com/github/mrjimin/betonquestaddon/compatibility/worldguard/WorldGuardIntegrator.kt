package com.github.mrjimin.betonquestaddon.compatibility.worldguard

import com.github.mrjimin.betonquestaddon.compatibility.ICompatibility
import com.github.mrjimin.betonquestaddon.compatibility.worldguard.condition.WGHasConditionFactory
import com.github.mrjimin.betonquestaddon.compatibility.worldguard.condition.WGIsConditionFactory
import org.betonquest.betonquest.api.BetonQuestApi

class WorldGuardIntegrator : ICompatibility {
    override fun hook(api: BetonQuestApi) {
        val questRegistries = api.questRegistries

        questRegistries.condition().apply {
            registerCombined("worldGuardHasOwner", WGHasConditionFactory(TargetType.OWNER))
            registerCombined("worldGuardHasMember", WGHasConditionFactory(TargetType.MEMBER))
            register("worldGuardIsOwner", WGIsConditionFactory(TargetType.OWNER))
            register("worldGuardIsMember", WGIsConditionFactory(TargetType.MEMBER))
        }
    }
}