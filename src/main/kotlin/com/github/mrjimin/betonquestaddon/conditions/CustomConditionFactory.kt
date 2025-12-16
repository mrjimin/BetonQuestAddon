package com.github.mrjimin.betonquestaddon.conditions

import org.betonquest.betonquest.api.instruction.Instruction
import org.betonquest.betonquest.api.instruction.argument.Argument
import org.betonquest.betonquest.api.quest.PrimaryServerThreadData
import org.betonquest.betonquest.api.quest.condition.PlayerCondition
import org.betonquest.betonquest.api.quest.condition.PlayerConditionFactory
import org.betonquest.betonquest.api.quest.condition.PlayerlessCondition
import org.betonquest.betonquest.api.quest.condition.PlayerlessConditionFactory
import org.betonquest.betonquest.api.quest.condition.nullable.NullableConditionAdapter
import org.betonquest.betonquest.api.quest.condition.thread.PrimaryServerThreadPlayerCondition
import org.betonquest.betonquest.api.quest.condition.thread.PrimaryServerThreadPlayerlessCondition
import org.bukkit.Location

class CustomConditionFactory(
    private val data: PrimaryServerThreadData,
    private val mechanicIdProvider: (Location) -> String?
) : PlayerConditionFactory, PlayerlessConditionFactory {

    override fun parsePlayer(instruction: Instruction): PlayerCondition =
        PrimaryServerThreadPlayerCondition(
            NullableConditionAdapter(parseInstruction(instruction)),
            data
        )

    override fun parsePlayerless(instruction: Instruction): PlayerlessCondition =
        PrimaryServerThreadPlayerlessCondition(
            NullableConditionAdapter(parseInstruction(instruction)),
            data
        )

    private fun parseInstruction(instruction: Instruction): CustomCondition {
        val itemId = instruction[Argument.STRING]
        val location = instruction[Argument.LOCATION]
        return CustomCondition(itemId, location, mechanicIdProvider)
    }
}
