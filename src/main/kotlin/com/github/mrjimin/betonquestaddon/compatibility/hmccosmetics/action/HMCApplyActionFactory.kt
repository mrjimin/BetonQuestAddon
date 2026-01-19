package com.github.mrjimin.betonquestaddon.compatibility.hmccosmetics.action

import org.betonquest.betonquest.api.instruction.Instruction
import org.betonquest.betonquest.api.quest.action.PlayerAction
import org.betonquest.betonquest.api.quest.action.PlayerActionFactory

class HMCApplyActionFactory : PlayerActionFactory {
    override fun parsePlayer(instruction: Instruction): PlayerAction {
        val cosmetics = instruction.string().get()
        val ignore = instruction.bool().get("ignore", false)
        return HMCApplyAction(cosmetics, ignore)
    }
}