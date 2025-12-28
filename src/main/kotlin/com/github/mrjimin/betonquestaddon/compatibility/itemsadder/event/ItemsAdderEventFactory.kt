package com.github.mrjimin.betonquestaddon.compatibility.itemsadder.event

import com.github.mrjimin.betonquestaddon.compatibility.itemsadder.CustomStackParser
import com.github.mrjimin.betonquestaddon.util.event.TargetType
import org.betonquest.betonquest.api.instruction.Instruction
import org.betonquest.betonquest.api.quest.event.PlayerEvent
import org.betonquest.betonquest.api.quest.event.PlayerEventFactory

class ItemsAdderEventFactory(
    private val targetType: TargetType
) : PlayerEventFactory {

    override fun parsePlayer(instruction: Instruction): PlayerEvent {
        val customStack = instruction.parse(CustomStackParser).get()
        val location = instruction.location().get()

        return ItemsAdderEvent(customStack, location, targetType)
    }

}
