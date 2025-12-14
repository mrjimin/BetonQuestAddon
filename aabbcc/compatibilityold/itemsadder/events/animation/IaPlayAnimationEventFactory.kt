package com.github.mrjimin.betonquestaddon.compatibilityold.itemsadder.events.animation

import com.github.mrjimin.betonquestaddon.betonquest.BetonQuestAddon
import org.betonquest.betonquest.api.instruction.Instruction
import org.betonquest.betonquest.api.instruction.argument.Argument
import org.betonquest.betonquest.api.quest.PrimaryServerThreadData
import org.betonquest.betonquest.api.quest.event.PlayerEvent
import org.betonquest.betonquest.api.quest.event.PlayerEventFactory
import org.betonquest.betonquest.api.quest.event.online.OnlineEventAdapter
import org.betonquest.betonquest.api.quest.event.thread.PrimaryServerThreadEvent

class IaPlayAnimationEventFactory(
    private val data: PrimaryServerThreadData
) : PlayerEventFactory {

    override fun parsePlayer(instruction: Instruction): PlayerEvent {
        val animation = instruction.get(Argument.STRING)
        return PrimaryServerThreadEvent(
            OnlineEventAdapter(
                IaPlayAnimation(animation),
                BetonQuestAddon.logger,
                instruction.getPackage()
            ), data
        )
    }
}