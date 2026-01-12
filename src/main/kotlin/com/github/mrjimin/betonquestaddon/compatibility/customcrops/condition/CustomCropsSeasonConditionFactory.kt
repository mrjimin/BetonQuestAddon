package com.github.mrjimin.betonquestaddon.compatibility.customcrops.condition

import net.momirealms.customcrops.api.core.world.Season
import org.betonquest.betonquest.api.instruction.Instruction
import org.betonquest.betonquest.api.quest.condition.PlayerCondition
import org.betonquest.betonquest.api.quest.condition.PlayerConditionFactory
import org.betonquest.betonquest.api.quest.condition.PlayerlessCondition
import org.betonquest.betonquest.api.quest.condition.PlayerlessConditionFactory
import org.betonquest.betonquest.api.quest.condition.nullable.NullableConditionAdapter
import org.betonquest.betonquest.quest.condition.ThrowExceptionPlayerlessCondition

class CustomCropsSeasonConditionFactory : PlayerConditionFactory, PlayerlessConditionFactory {
    override fun parsePlayer(instruction: Instruction): PlayerCondition {
        val season = instruction.enumeration(Season::class.java).get()
        val locationWorld = instruction.string().get("world", "%location.world%")
        val world = instruction.chainForArgument(locationWorld.getValue(null)).world().get()
        return NullableConditionAdapter(CustomCropsSeasonCondition(season, world))
    }

    override fun parsePlayerless(instruction: Instruction): PlayerlessCondition {
        val season = instruction.enumeration(Season::class.java).get()
        val optionalWorld = instruction.world().get("world")
        return optionalWorld.map { world ->
            NullableConditionAdapter(CustomCropsSeasonCondition(season, world)) as PlayerlessCondition
        }.orElse(ThrowExceptionPlayerlessCondition())
    }

}