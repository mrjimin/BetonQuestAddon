package com.github.mrjimin.betonquestaddon.compatibility.customnameplates.condition

import com.github.mrjimin.betonquestaddon.compatibility.customnameplates.NpCheckType
import org.betonquest.betonquest.api.instruction.Instruction
import org.betonquest.betonquest.api.quest.condition.PlayerCondition
import org.betonquest.betonquest.api.quest.condition.PlayerConditionFactory

class NpEquippedConditionFactory(
    private val type: NpCheckType
): PlayerConditionFactory {
    override fun parsePlayer(instruction: Instruction): PlayerCondition {
        val target = instruction.string().get()
        return NpEquippedCondition(type, target)
    }
}