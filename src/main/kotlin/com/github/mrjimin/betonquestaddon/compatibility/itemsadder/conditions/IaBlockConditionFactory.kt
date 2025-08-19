package com.github.mrjimin.betonquestaddon.compatibility.itemsadder.conditions

import com.github.mrjimin.betonquestaddon.compatibility.itemsadder.IaParser
import org.betonquest.betonquest.api.quest.condition.PlayerCondition
import org.betonquest.betonquest.api.quest.condition.PlayerConditionFactory
import org.betonquest.betonquest.api.quest.condition.PlayerlessCondition
import org.betonquest.betonquest.api.quest.condition.PlayerlessConditionFactory
import org.betonquest.betonquest.api.quest.condition.nullable.NullableConditionAdapter
import org.betonquest.betonquest.api.instruction.Instruction
import org.betonquest.betonquest.api.instruction.argument.Argument
import org.betonquest.betonquest.quest.PrimaryServerThreadData
import org.betonquest.betonquest.quest.condition.PrimaryServerThreadPlayerCondition
import org.betonquest.betonquest.quest.condition.PrimaryServerThreadPlayerlessCondition

class IaBlockConditionFactory(
    private val data: PrimaryServerThreadData
) : PlayerConditionFactory, PlayerlessConditionFactory {

    override fun parsePlayer(instruction: Instruction): PlayerCondition {
        return PrimaryServerThreadPlayerCondition(
            NullableConditionAdapter(parseInstruction(instruction)), data
        )
    }

    override fun parsePlayerless(instruction: Instruction): PlayerlessCondition {
        return PrimaryServerThreadPlayerlessCondition(
            NullableConditionAdapter(parseInstruction(instruction)), data
        )
    }

    private fun parseInstruction(instruction: Instruction): IaBlock {
        val itemID = instruction.get(IaParser)
        val location = instruction.get(Argument.LOCATION)
        return IaBlock(itemID, location)
    }
}
