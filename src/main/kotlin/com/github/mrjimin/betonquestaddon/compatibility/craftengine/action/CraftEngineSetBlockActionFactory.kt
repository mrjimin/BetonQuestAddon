package com.github.mrjimin.betonquestaddon.compatibility.craftengine.action

import org.betonquest.betonquest.api.instruction.Instruction
import org.betonquest.betonquest.api.quest.action.PlayerAction
import org.betonquest.betonquest.api.quest.action.PlayerActionFactory

class CraftEngineSetBlockActionFactory : PlayerActionFactory {

    override fun parsePlayer(instruction: Instruction): PlayerAction {
        val itemId = instruction.string().get()
        val location = instruction.location().get()
        val playSound = instruction.bool().get("playSound", false)
        return CraftEngineSetBlockAction(itemId, location, playSound)
    }

}