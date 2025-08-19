package com.github.mrjimin.betonquestaddon.compatibility.placeholderapi.conditions

import com.github.mrjimin.betonquestaddon.betonquest.parser.OperatorParser
import org.betonquest.betonquest.api.quest.condition.PlayerCondition
import org.betonquest.betonquest.api.quest.condition.PlayerConditionFactory
import org.betonquest.betonquest.api.instruction.Instruction
import org.betonquest.betonquest.api.instruction.argument.Argument
import org.betonquest.betonquest.api.instruction.variable.Variable
import org.betonquest.betonquest.quest.PrimaryServerThreadData
import org.betonquest.betonquest.quest.condition.PrimaryServerThreadPlayerCondition

class PAPIConditionFactory(
    private val data: PrimaryServerThreadData
) : PlayerConditionFactory {

    override fun parsePlayer(instruction: Instruction): PlayerCondition {
        val first = instruction.get(Argument.STRING)
        val op = instruction.get(OperatorParser)
        val second = instruction.get(Argument.STRING)
        val ignoreCase = instruction.getValue("ignoreCase", Argument.BOOLEAN) ?: Variable(false)
        return PrimaryServerThreadPlayerCondition(PAPIConditions(first, op, second, ignoreCase), data)
    }
}