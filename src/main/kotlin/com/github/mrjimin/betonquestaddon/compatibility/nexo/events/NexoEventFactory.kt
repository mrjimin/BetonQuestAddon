package com.github.mrjimin.betonquestaddon.compatibility.nexo.events

import com.github.mrjimin.betonquestaddon.util.event.TargetType
import org.betonquest.betonquest.api.instruction.Instruction
import org.betonquest.betonquest.api.quest.event.PlayerEvent
import org.betonquest.betonquest.api.quest.event.PlayerEventFactory

class NexoEventFactory(
    private val targetType: TargetType
) : PlayerEventFactory {

    override fun parsePlayer(instruction: Instruction): PlayerEvent {
        val itemId = instruction.string().get()
        val location = instruction.location().get()

        val rotation = instruction.string().get("rotation", "NONE")
        val blockFace = instruction.string().get("blockFace", "SELF")

        return NexoEvent(itemId, location, rotation, blockFace, targetType)
    }

}
