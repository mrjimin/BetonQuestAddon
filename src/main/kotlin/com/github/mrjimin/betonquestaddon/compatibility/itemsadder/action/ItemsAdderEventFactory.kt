package com.github.mrjimin.betonquestaddon.compatibility.itemsadder.action

import com.github.mrjimin.betonquestaddon.compatibility.itemsadder.CustomStackParser
import com.github.mrjimin.betonquestaddon.util.action.TargetType
import org.betonquest.betonquest.api.instruction.Instruction
import org.betonquest.betonquest.api.quest.action.PlayerAction
import org.betonquest.betonquest.api.quest.action.PlayerActionFactory

class ItemsAdderEventFactory(
    private val targetType: TargetType
) : PlayerActionFactory {

    override fun parsePlayer(instruction: Instruction): PlayerAction {
        val customStack = instruction.parse(CustomStackParser).get()
        val location = instruction.location().get()

        return ItemsAdderEvent(customStack, location, targetType)
    }

}
