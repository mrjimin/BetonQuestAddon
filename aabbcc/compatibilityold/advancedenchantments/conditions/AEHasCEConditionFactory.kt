package com.github.mrjimin.betonquestaddon.compatibilityold.advancedenchantments.conditions

import com.github.mrjimin.betonquestaddon.betonquest.parser.OperatorParser
import org.betonquest.betonquest.api.instruction.Instruction
import org.betonquest.betonquest.api.instruction.argument.Argument
import org.betonquest.betonquest.api.quest.condition.PlayerCondition
import org.betonquest.betonquest.api.quest.condition.PlayerConditionFactory
import org.betonquest.betonquest.api.quest.PrimaryServerThreadData
import org.betonquest.betonquest.api.quest.condition.thread.PrimaryServerThreadPlayerCondition

class AEHasCEConditionFactory(
    private val data: PrimaryServerThreadData
) : PlayerConditionFactory {

    override fun parsePlayer(instruction: Instruction): PlayerCondition {
        val group = instruction.get(Argument.STRING)
        val op = instruction.get(OperatorParser)
        val amount = instruction.get(Argument.NUMBER)
        return PrimaryServerThreadPlayerCondition(AEHasCECondition(group, op, amount), data)
    }
}