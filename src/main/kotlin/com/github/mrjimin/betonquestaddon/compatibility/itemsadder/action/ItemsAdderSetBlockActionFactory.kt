package com.github.mrjimin.betonquestaddon.compatibility.itemsadder.action

import org.betonquest.betonquest.api.instruction.Instruction
import org.betonquest.betonquest.api.quest.action.PlayerAction
import org.betonquest.betonquest.api.quest.action.PlayerActionFactory

class ItemsAdderSetBlockActionFactory : PlayerActionFactory {

    override fun parsePlayer(instruction: Instruction): PlayerAction {
        val itemId = instruction.string().get()
        val location = instruction.location().get()
        return ItemsAdderSetBlockAction(itemId, location)
    }

}