package com.github.mrjimin.betonquestaddon.compatibility.customnameplates.action

import org.betonquest.betonquest.api.instruction.Instruction
import org.betonquest.betonquest.api.quest.action.PlayerAction
import org.betonquest.betonquest.api.quest.action.PlayerActionFactory

class NpApplyActionFactory : PlayerActionFactory {
    override fun parsePlayer(instruction: Instruction): PlayerAction {
        val nameplate = instruction.string().get()
        return NpApplyAction(nameplate)
    }
}