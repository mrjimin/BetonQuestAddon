package com.github.mrjimin.betonquestaddon.compatibility.itemsadder.event

import com.github.mrjimin.betonquestaddon.compatibility.itemsadder.CustomStackParser
import com.github.mrjimin.betonquestaddon.util.event.TargetType
import org.betonquest.betonquest.api.instruction.Instruction
import org.betonquest.betonquest.api.instruction.argument.Argument
import org.betonquest.betonquest.api.quest.PrimaryServerThreadData
import org.betonquest.betonquest.api.quest.event.PlayerEvent
import org.betonquest.betonquest.api.quest.event.PlayerEventFactory
import org.betonquest.betonquest.api.quest.event.thread.PrimaryServerThreadEvent

class ItemsAdderEventFactory(
    private val data: PrimaryServerThreadData,
    private val targetType: TargetType
) : PlayerEventFactory {

    override fun parsePlayer(instruction: Instruction): PlayerEvent {
        val customStack = instruction[CustomStackParser]
        val location = instruction[Argument.LOCATION]

        return PrimaryServerThreadEvent(
            ItemsAdderEvent(customStack, location, targetType),
            data
        )
    }

}
