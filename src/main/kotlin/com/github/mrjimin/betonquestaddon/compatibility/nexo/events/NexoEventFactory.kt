package com.github.mrjimin.betonquestaddon.compatibility.nexo.events

import com.github.mrjimin.betonquestaddon.util.event.TargetType
import com.github.mrjimin.betonquestaddon.util.getOrNull
import org.betonquest.betonquest.api.instruction.Instruction
import org.betonquest.betonquest.api.instruction.argument.Argument
import org.betonquest.betonquest.api.quest.PrimaryServerThreadData
import org.betonquest.betonquest.api.quest.event.PlayerEvent
import org.betonquest.betonquest.api.quest.event.PlayerEventFactory
import org.betonquest.betonquest.api.quest.event.thread.PrimaryServerThreadEvent

class NexoEventFactory(
    private val data: PrimaryServerThreadData,
    private val targetType: TargetType
) : PlayerEventFactory {

    override fun parsePlayer(instruction: Instruction): PlayerEvent {
        val itemId = instruction[Argument.STRING]
        val location = instruction[Argument.LOCATION]

        // val rotation = instruction.getOrNull(Argument.STRING)
        val rotation = instruction.getValue("rotation", Argument.STRING)
        val blockFace = instruction.getValue("blockFace", Argument.STRING)

        return PrimaryServerThreadEvent(
            NexoEvent(itemId, location, rotation, blockFace, targetType),
            data
        )
    }

}
