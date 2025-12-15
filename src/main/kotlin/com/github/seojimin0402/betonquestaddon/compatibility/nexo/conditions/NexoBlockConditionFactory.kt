package com.github.seojimin0402.betonquestaddon.compatibility.nexo.conditions

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

class NexoBlockConditionFactory(
    private val data: PrimaryServerThreadData
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

    private fun parseInstruction(instruction: Instruction): NexoBlockCondition {
        val itemID= instruction[Argument.STRING]
        val location = instruction[Argument.LOCATION]
        return NexoBlockCondition(itemID, location)
    }
}