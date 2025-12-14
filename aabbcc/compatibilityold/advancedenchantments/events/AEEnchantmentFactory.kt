package com.github.mrjimin.betonquestaddon.compatibilityold.advancedenchantments.events

import org.betonquest.betonquest.api.instruction.Instruction
import org.betonquest.betonquest.api.instruction.argument.Argument
import org.betonquest.betonquest.api.quest.event.PlayerEvent
import org.betonquest.betonquest.api.quest.event.PlayerEventFactory
import org.betonquest.betonquest.api.quest.PrimaryServerThreadData
import org.betonquest.betonquest.api.quest.event.thread.PrimaryServerThreadEvent

class AEEnchantmentFactory(
    private val data: PrimaryServerThreadData
) : PlayerEventFactory {
    override fun parsePlayer(instruction: Instruction): PlayerEvent {
        val name = instruction.get(Argument.STRING)
        val level = instruction.get(Argument.NUMBER)
        return PrimaryServerThreadEvent(AEEnchantment(name, level), data)
    }
}