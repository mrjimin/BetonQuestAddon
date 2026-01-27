package kr.mrjimin.betonquestaddon.compatibility.customcrops.objective.common

import kr.mrjimin.betonquestaddon.config.NotifyMessage
import kr.mrjimin.betonquestaddon.util.action.Action
import net.momirealms.customcrops.api.event.ScarecrowBreakEvent
import net.momirealms.customcrops.api.event.ScarecrowPlaceEvent
import org.betonquest.betonquest.api.DefaultObjective
import org.betonquest.betonquest.api.QuestException
import org.betonquest.betonquest.api.instruction.Instruction
import org.betonquest.betonquest.api.quest.objective.ObjectiveFactory
import org.betonquest.betonquest.api.quest.objective.service.ObjectiveService

class ScarecrowObjectiveFactory(
    private val action: Action,
    private val notifyMessage: NotifyMessage
) : ObjectiveFactory {
    override fun parseInstruction(instruction: Instruction, service: ObjectiveService): DefaultObjective {
        val id = instruction.string().list().get()
        val targetAmount = instruction.number().get("amount", 1)

        val objective = ScarecrowObjective(service, targetAmount, id, notifyMessage)

        return when (action) {
            Action.PLACE -> service.request(ScarecrowPlaceEvent::class.java).handler(objective::onPlace)
            Action.BREAK -> service.request(ScarecrowBreakEvent::class.java).handler(objective::onBreak)
            else -> throw QuestException("Scarecrow objective only supports PLACE or BREAK.")
        }.subscribe(true).let { objective }
    }
}