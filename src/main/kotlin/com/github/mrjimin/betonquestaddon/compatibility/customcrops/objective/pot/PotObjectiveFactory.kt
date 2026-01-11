package com.github.mrjimin.betonquestaddon.compatibility.customcrops.objective.pot

import com.github.mrjimin.betonquestaddon.config.NotifyMessage
import com.github.mrjimin.betonquestaddon.util.action.Action
import net.momirealms.customcrops.api.event.PotBreakEvent
import net.momirealms.customcrops.api.event.PotPlaceEvent
import org.betonquest.betonquest.api.DefaultObjective
import org.betonquest.betonquest.api.QuestException
import org.betonquest.betonquest.api.instruction.Instruction
import org.betonquest.betonquest.api.quest.objective.ObjectiveFactory
import org.betonquest.betonquest.api.quest.objective.service.ObjectiveService

class PotObjectiveFactory(
    private val action: Action,
    private val notifyMessage: NotifyMessage
) : ObjectiveFactory {
    override fun parseInstruction(instruction: Instruction, service: ObjectiveService): DefaultObjective {
        val id = instruction.string().list().get()
        val targetAmount = instruction.number().get("amount", 1)

        val objective = PotObjective(service, targetAmount, id, notifyMessage)

        return when (action) {
            Action.PLACE -> service.request(PotPlaceEvent::class.java).handler(objective::onPlace)
            Action.BREAK -> service.request(PotBreakEvent::class.java).handler(objective::onBreak)
            else -> throw QuestException("Pot objective only supports PLACE or BREAK.")
        }.subscribe(true).let { objective }
    }
}