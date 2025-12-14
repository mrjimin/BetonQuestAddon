package com.github.mrjimin.betonquestaddon.compatibilityold.itemsadder.events.block

import com.github.mrjimin.betonquestaddon.compatibilityold.itemsadder.IaParser
import dev.lone.itemsadder.api.CustomStack
import org.betonquest.betonquest.api.quest.event.PlayerEvent
import org.betonquest.betonquest.api.quest.event.PlayerEventFactory
import org.betonquest.betonquest.api.instruction.Instruction
import org.betonquest.betonquest.api.instruction.argument.Argument
import org.betonquest.betonquest.api.instruction.variable.Variable
import org.betonquest.betonquest.api.quest.PrimaryServerThreadData
import org.betonquest.betonquest.api.quest.event.thread.PrimaryServerThreadEvent

class IaSetBlockAtEventFactory(
    private val data: PrimaryServerThreadData
) : PlayerEventFactory {

    override fun parsePlayer(instruction: Instruction): PlayerEvent {
        val itemID: Variable<CustomStack> = instruction.get(IaParser)
        val location = instruction.get(Argument.LOCATION)
        return PrimaryServerThreadEvent(IaSetBlockAt(itemID, location), data)
    }
}
