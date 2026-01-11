package com.github.mrjimin.betonquestaddon.compatibility.customcrops.objective.sprinkler

import com.github.mrjimin.betonquestaddon.config.NotifyMessage
import com.github.mrjimin.betonquestaddon.util.action.Action
import net.momirealms.customcrops.api.event.SprinklerBreakEvent
import net.momirealms.customcrops.api.event.SprinklerPlaceEvent
import org.betonquest.betonquest.api.DefaultObjective
import org.betonquest.betonquest.api.QuestException
import org.betonquest.betonquest.api.instruction.Instruction
import org.betonquest.betonquest.api.quest.objective.ObjectiveFactory
import org.betonquest.betonquest.api.quest.objective.service.ObjectiveService

class SprinklerObjectiveFactory(
    private val action: Action,
    private val notifyMessage: NotifyMessage
) : ObjectiveFactory {
    override fun parseInstruction(instruction: Instruction, service: ObjectiveService): DefaultObjective {
        val id = instruction.string().list().get()
        val targetAmount = instruction.number().get("amount", 1)

        val objective = SprinklerObjective(service, targetAmount, id, notifyMessage)

        return when (action) {
            Action.PLACE -> service.request(SprinklerPlaceEvent::class.java).handler(objective::onPlace)
            Action.BREAK -> service.request(SprinklerBreakEvent::class.java).handler(objective::onBreak)
            else -> throw QuestException("Sprinkler objective only supports PLACE or BREAK.")
        }.subscribe(true).let { objective }
    }
}