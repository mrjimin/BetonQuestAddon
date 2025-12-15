package com.github.seojimin0402.betonquestaddon.compatibility.nexo.events

import com.github.seojimin0402.betonquestaddon.util.event.TargetType
import com.github.seojimin0402.betonquestaddon.util.getOrNull
import org.betonquest.betonquest.api.instruction.Instruction
import org.betonquest.betonquest.api.instruction.argument.Argument
import org.betonquest.betonquest.api.quest.PrimaryServerThreadData
import org.betonquest.betonquest.api.quest.event.PlayerEvent
import org.betonquest.betonquest.api.quest.event.PlayerEventFactory
import org.betonquest.betonquest.api.quest.event.thread.PrimaryServerThreadEvent

class NexoEventFactory(
    private val data: PrimaryServerThreadData,
    private val type: TargetType
) : PlayerEventFactory {

    override fun parsePlayer(instruction: Instruction): PlayerEvent {
        val itemId = instruction[Argument.STRING]
        val location = instruction[Argument.LOCATION]

        val rotation = instruction.getOrNull(Argument.STRING)
        val blockFace = instruction.getOrNull(Argument.STRING)

        return PrimaryServerThreadEvent(
            NexoEvent(itemId, location, rotation, blockFace, type),
            data
        )
    }
}
