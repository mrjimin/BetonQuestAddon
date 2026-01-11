package com.github.mrjimin.betonquestaddon.condition

import org.betonquest.betonquest.api.instruction.Instruction
import org.betonquest.betonquest.api.quest.condition.PlayerCondition
import org.betonquest.betonquest.api.quest.condition.PlayerConditionFactory
import org.betonquest.betonquest.api.quest.condition.PlayerlessCondition
import org.betonquest.betonquest.api.quest.condition.PlayerlessConditionFactory
import org.betonquest.betonquest.api.quest.condition.nullable.NullableConditionAdapter
import org.bukkit.Location

class LocationConditionFactory(
    private val mechanicIdProvider: (Location) -> String?
) : PlayerConditionFactory, PlayerlessConditionFactory {

    override fun parsePlayer(instruction: Instruction): PlayerCondition =
        NullableConditionAdapter(parseInstruction(instruction))

    override fun parsePlayerless(instruction: Instruction): PlayerlessCondition =
        NullableConditionAdapter(parseInstruction(instruction))

    private fun parseInstruction(instruction: Instruction): LocationCondition {
        val itemId = instruction.string().get()
        val location = instruction.location().get()
        return LocationCondition(itemId, location, mechanicIdProvider)
    }

}
