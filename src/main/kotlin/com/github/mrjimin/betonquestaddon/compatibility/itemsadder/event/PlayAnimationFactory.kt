package com.github.mrjimin.betonquestaddon.compatibility.itemsadder.event

import org.betonquest.betonquest.api.instruction.Instruction
import org.betonquest.betonquest.api.logger.BetonQuestLoggerFactory
import org.betonquest.betonquest.api.quest.event.PlayerEvent
import org.betonquest.betonquest.api.quest.event.PlayerEventFactory
import org.betonquest.betonquest.api.quest.event.online.OnlineEventAdapter

class PlayAnimationFactory(
    private val loggerFactory: BetonQuestLoggerFactory
) : PlayerEventFactory {

    override fun parsePlayer(instruction: Instruction): PlayerEvent? {
        val animation = instruction.string().get()
        return OnlineEventAdapter(
            PlayAnimation(animation),
            loggerFactory.create(PlayAnimation::class.java),
            instruction.`package`
        )
    }


}