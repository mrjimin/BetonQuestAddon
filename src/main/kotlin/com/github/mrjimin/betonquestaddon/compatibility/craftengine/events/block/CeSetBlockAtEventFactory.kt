package com.github.mrjimin.betonquestaddon.compatibility.craftengine.events.block

import com.github.mrjimin.betonquestaddon.hook.CraftEngineHook
import org.betonquest.betonquest.api.quest.event.PlayerEvent
import org.betonquest.betonquest.api.quest.event.PlayerEventFactory
import org.betonquest.betonquest.instruction.Instruction
import org.betonquest.betonquest.quest.PrimaryServerThreadData

class CeSetBlockAtEventFactory(
    private val data: PrimaryServerThreadData
) : PlayerEventFactory {

    override fun parsePlayer(instruction: Instruction): PlayerEvent {
        return CraftEngineHook.create(instruction, data) { id, loc, sound -> CeSetBlockAt(id, loc, sound) }
    }
}