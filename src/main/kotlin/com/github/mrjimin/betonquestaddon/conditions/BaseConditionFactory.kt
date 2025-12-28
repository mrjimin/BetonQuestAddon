package com.github.mrjimin.betonquestaddon.conditions

import org.betonquest.betonquest.api.instruction.Instruction
import org.betonquest.betonquest.api.quest.condition.PlayerCondition
import org.betonquest.betonquest.api.quest.condition.PlayerConditionFactory
import org.betonquest.betonquest.api.quest.condition.PlayerlessCondition
import org.betonquest.betonquest.api.quest.condition.PlayerlessConditionFactory
import org.betonquest.betonquest.api.quest.condition.nullable.NullableConditionAdapter
import org.bukkit.Location

class BaseConditionFactory(
    private val mechanicIdProvider: (Location) -> String?
) : PlayerConditionFactory, PlayerlessConditionFactory {

    override fun parsePlayer(instruction: Instruction): PlayerCondition =
        NullableConditionAdapter(parseInstruction(instruction))

    override fun parsePlayerless(instruction: Instruction): PlayerlessCondition =
        NullableConditionAdapter(parseInstruction(instruction))

    private fun parseInstruction(instruction: Instruction): BaseCondition {
        val itemId = instruction.string().get()
        val location = instruction.location().get()
        return BaseCondition(itemId, location, mechanicIdProvider)
    }

}
