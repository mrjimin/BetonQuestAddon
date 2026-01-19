package com.github.mrjimin.betonquestaddon.compatibility.cosmeticscore.condition

import org.betonquest.betonquest.api.instruction.Instruction
import org.betonquest.betonquest.api.quest.condition.PlayerCondition
import org.betonquest.betonquest.api.quest.condition.PlayerConditionFactory

class CCInWardrobeConditionFactory : PlayerConditionFactory {
    override fun parsePlayer(instruction: Instruction): PlayerCondition {
        return CCInWardrobeCondition()
    }
}