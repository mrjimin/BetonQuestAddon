package kr.mrjimin.betonquestaddon.compatibility.worldguard

import kr.mrjimin.betonquestaddon.compatibility.ICompatibility
import kr.mrjimin.betonquestaddon.compatibility.worldguard.condition.WGHasConditionFactory
import kr.mrjimin.betonquestaddon.compatibility.worldguard.condition.WGHasFlagConditionFactory
import kr.mrjimin.betonquestaddon.compatibility.worldguard.condition.WGIsConditionFactory
import org.betonquest.betonquest.api.BetonQuestApi

class WorldGuardIntegrator : ICompatibility {
    override fun hook(api: BetonQuestApi) {
        api.conditions().registry().apply {
            registerCombined("worldGuardHasOwner", WGHasConditionFactory(TargetType.OWNER))
            registerCombined("worldGuardHasMember", WGHasConditionFactory(TargetType.MEMBER))
            registerCombined("worldGuardHasFlag", WGHasFlagConditionFactory())
            register("worldGuardIsOwner", WGIsConditionFactory(TargetType.OWNER))
            register("worldGuardIsMember", WGIsConditionFactory(TargetType.MEMBER))
        }
    }
}