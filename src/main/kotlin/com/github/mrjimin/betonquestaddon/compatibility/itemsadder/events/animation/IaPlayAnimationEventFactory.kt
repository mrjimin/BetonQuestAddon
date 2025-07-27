package com.github.mrjimin.betonquestaddon.compatibility.itemsadder.events.animation

import org.betonquest.betonquest.api.logger.BetonQuestLoggerFactory
import org.betonquest.betonquest.api.quest.event.PlayerEvent
import org.betonquest.betonquest.api.quest.event.PlayerEventFactory
import org.betonquest.betonquest.api.quest.event.online.OnlineEventAdapter
import org.betonquest.betonquest.instruction.Instruction
import org.betonquest.betonquest.instruction.argument.Argument
import org.betonquest.betonquest.quest.PrimaryServerThreadData
import org.betonquest.betonquest.quest.event.PrimaryServerThreadEvent

class IaPlayAnimationEventFactory(
    private val loggerFactory: BetonQuestLoggerFactory,
    private val data: PrimaryServerThreadData
) : PlayerEventFactory {

    override fun parsePlayer(instruction: Instruction): PlayerEvent {
        val animation = instruction.get(Argument.STRING)
        return PrimaryServerThreadEvent(
            OnlineEventAdapter(
                IaPlayAnimation(animation),
                loggerFactory.create(IaPlayAnimation::class.java),
                instruction.getPackage()
            ), data
        )
    }
}