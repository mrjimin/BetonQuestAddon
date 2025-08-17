package com.github.mrjimin.betonquestaddon.compatibility.worldguard.conditions

import com.github.mrjimin.betonquestaddon.betonquest.parser.HasParser
import com.github.mrjimin.betonquestaddon.betonquest.parser.StringListParser
import org.betonquest.betonquest.api.quest.condition.PlayerCondition
import org.betonquest.betonquest.api.quest.condition.PlayerConditionFactory
import org.betonquest.betonquest.instruction.Instruction
import org.betonquest.betonquest.instruction.argument.Argument
import org.betonquest.betonquest.quest.PrimaryServerThreadData
import org.betonquest.betonquest.quest.condition.PrimaryServerThreadPlayerCondition

class WGFlagsFactory(
    private val data: PrimaryServerThreadData
) : PlayerConditionFactory {

    override fun parsePlayer(instruction: Instruction): PlayerCondition {
        val region = instruction.get(Argument.STRING)
        val mode = instruction.get(HasParser)
        val flags = instruction.get(StringListParser)

        return PrimaryServerThreadPlayerCondition(WGFlags(region, mode, flags), data)
    }
}