package com.github.mrjimin.betonquestaddon.compatibilityold.worldguard.conditions

import com.github.mrjimin.betonquestaddon.util.getOrNull
import org.betonquest.betonquest.api.quest.condition.PlayerCondition
import org.betonquest.betonquest.api.quest.condition.PlayerConditionFactory
import org.betonquest.betonquest.api.instruction.Instruction
import org.betonquest.betonquest.api.instruction.argument.Argument
import org.betonquest.betonquest.api.quest.PrimaryServerThreadData
import org.betonquest.betonquest.api.quest.condition.thread.PrimaryServerThreadPlayerCondition

class WGIsMemberFactory(
    private val data: PrimaryServerThreadData
) : PlayerConditionFactory {
    override fun parsePlayer(instruction: Instruction): PlayerCondition =
        PrimaryServerThreadPlayerCondition(WGIsMember(instruction.getOrNull(Argument.STRING)), data)
}