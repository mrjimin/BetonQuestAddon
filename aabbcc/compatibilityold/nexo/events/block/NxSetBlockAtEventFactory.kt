package com.github.mrjimin.betonquestaddon.compatibilityold.nexo.events.block

import com.github.mrjimin.betonquestaddon.compatibilityold.nexo.NxParser
import org.betonquest.betonquest.api.quest.event.PlayerEvent
import org.betonquest.betonquest.api.quest.event.PlayerEventFactory
import org.betonquest.betonquest.api.instruction.Instruction
import org.betonquest.betonquest.api.instruction.argument.Argument
import org.betonquest.betonquest.api.quest.PrimaryServerThreadData
import org.betonquest.betonquest.api.quest.event.thread.PrimaryServerThreadEvent

class NxSetBlockAtEventFactory(
    private val data: PrimaryServerThreadData
) : PlayerEventFactory {

    override fun parsePlayer(instruction: Instruction): PlayerEvent {
        val itemID = instruction.get(NxParser)
        val location = instruction.get(Argument.LOCATION)
        return PrimaryServerThreadEvent(NxSetBlockAt(itemID, location), data)
    }
}