package com.github.mrjimin.betonquestaddon.compatibility.worldguard.conditions

import com.github.mrjimin.betonquestaddon.util.getOrNull
import org.betonquest.betonquest.api.quest.condition.PlayerCondition
import org.betonquest.betonquest.api.quest.condition.PlayerConditionFactory
import org.betonquest.betonquest.api.instruction.Instruction
import org.betonquest.betonquest.api.instruction.argument.Argument
import org.betonquest.betonquest.quest.PrimaryServerThreadData
import org.betonquest.betonquest.quest.condition.PrimaryServerThreadPlayerCondition

class WGHasOwnerFactory(
    private val data: PrimaryServerThreadData
) : PlayerConditionFactory {
    override fun parsePlayer(instruction: Instruction): PlayerCondition =
        PrimaryServerThreadPlayerCondition(WGHasOwner(instruction.getOrNull(Argument.STRING)), data)
}