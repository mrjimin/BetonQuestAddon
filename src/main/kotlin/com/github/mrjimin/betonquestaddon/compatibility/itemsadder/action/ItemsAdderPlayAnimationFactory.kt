package com.github.mrjimin.betonquestaddon.compatibility.itemsadder.action

import org.betonquest.betonquest.api.instruction.Instruction
import org.betonquest.betonquest.api.logger.BetonQuestLoggerFactory
import org.betonquest.betonquest.api.quest.action.PlayerAction
import org.betonquest.betonquest.api.quest.action.PlayerActionFactory
import org.betonquest.betonquest.api.quest.action.online.OnlineActionAdapter

class ItemsAdderPlayAnimationFactory(
    private val loggerFactory: BetonQuestLoggerFactory
) : PlayerActionFactory {

    override fun parsePlayer(instruction: Instruction): PlayerAction? {
        val animation = instruction.string().get()
        return OnlineActionAdapter(
            ItemsAdderPlayAnimation(animation),
            loggerFactory.create(ItemsAdderPlayAnimation::class.java),
            instruction.`package`
        )
    }


}