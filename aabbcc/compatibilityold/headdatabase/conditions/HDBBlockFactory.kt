package com.github.mrjimin.betonquestaddon.compatibilityold.headdatabase.conditions

import com.github.mrjimin.betonquestaddon.compatibilityold.headdatabase.HDBParser
import org.betonquest.betonquest.api.quest.condition.PlayerCondition
import org.betonquest.betonquest.api.quest.condition.PlayerConditionFactory
import org.betonquest.betonquest.api.quest.condition.PlayerlessCondition
import org.betonquest.betonquest.api.quest.condition.PlayerlessConditionFactory
import org.betonquest.betonquest.api.quest.condition.nullable.NullableConditionAdapter
import org.betonquest.betonquest.api.instruction.Instruction
import org.betonquest.betonquest.api.instruction.argument.Argument
import org.betonquest.betonquest.api.quest.PrimaryServerThreadData
import org.betonquest.betonquest.api.quest.condition.thread.PrimaryServerThreadPlayerCondition
import org.betonquest.betonquest.api.quest.condition.thread.PrimaryServerThreadPlayerlessCondition

class HDBBlockFactory(
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

    private fun parseInstruction(instruction: Instruction): HDBBlock {
        val itemID = instruction.get(HDBParser)
        val location = instruction.get(Argument.LOCATION)
        println(itemID)
        println(location)
        return HDBBlock(itemID, location)
    }
}