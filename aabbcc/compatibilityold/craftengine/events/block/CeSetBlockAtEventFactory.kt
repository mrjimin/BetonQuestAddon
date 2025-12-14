package com.github.mrjimin.betonquestaddon.compatibilityold.craftengine.events.block

import com.github.mrjimin.betonquestaddon.hook.CraftEngineHook
import org.betonquest.betonquest.api.instruction.Instruction
import org.betonquest.betonquest.api.quest.PrimaryServerThreadData
import org.betonquest.betonquest.api.quest.event.PlayerEvent
import org.betonquest.betonquest.api.quest.event.PlayerEventFactory

class CeSetBlockAtEventFactory(
    private val data: PrimaryServerThreadData
) : PlayerEventFactory {

    override fun parsePlayer(instruction: Instruction): PlayerEvent {
        return CraftEngineHook.create(instruction, data) { id, loc, sound -> CeSetBlockAt(id, loc, sound) }
    }
}