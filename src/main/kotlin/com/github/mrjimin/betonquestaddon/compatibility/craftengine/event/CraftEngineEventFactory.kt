package com.github.mrjimin.betonquestaddon.compatibility.craftengine.event

import com.github.mrjimin.betonquestaddon.util.event.TargetType
import org.betonquest.betonquest.api.instruction.Instruction
import org.betonquest.betonquest.api.quest.event.PlayerEvent
import org.betonquest.betonquest.api.quest.event.PlayerEventFactory

class CraftEngineEventFactory(
    private val targetType: TargetType
) : PlayerEventFactory {

    override fun parsePlayer(instruction: Instruction): PlayerEvent {
        val itemId = instruction.string().get()
        val location = instruction.location().get()

        val playSound = instruction.bool().get("playSound", false)

        return CraftEngineEvent(itemId, location, playSound, targetType)
    }

}