package com.github.mrjimin.betonquestaddon.compatibility.hmccosmetics.condition

import org.betonquest.betonquest.api.instruction.Instruction
import org.betonquest.betonquest.api.quest.condition.PlayerCondition
import org.betonquest.betonquest.api.quest.condition.PlayerConditionFactory

class HMCInWardrobeConditionFactory : PlayerConditionFactory {
    override fun parsePlayer(instruction: Instruction): PlayerCondition {
        return HMCInWardrobeCondition()
    }
}