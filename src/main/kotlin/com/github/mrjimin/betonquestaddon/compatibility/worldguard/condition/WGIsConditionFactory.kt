package com.github.mrjimin.betonquestaddon.compatibility.worldguard.condition

import com.github.mrjimin.betonquestaddon.compatibility.worldguard.TargetType
import org.betonquest.betonquest.api.instruction.Instruction
import org.betonquest.betonquest.api.quest.condition.PlayerCondition
import org.betonquest.betonquest.api.quest.condition.PlayerConditionFactory

class WGIsConditionFactory(
    private val targetType: TargetType,
) : PlayerConditionFactory {

    override fun parsePlayer(instruction: Instruction): PlayerCondition {
        return WGIsCondition(targetType)
    }

}