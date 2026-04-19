package kr.mrjimin.betonquestaddon.compatibility.customcrops.objective.crop

import kr.mrjimin.betonquestaddon.config.NotifyMessage
import kr.mrjimin.betonquestaddon.util.action.Action
import net.momirealms.customcrops.api.event.CropBreakEvent
import net.momirealms.customcrops.api.event.CropPlantEvent
import org.betonquest.betonquest.api.DefaultObjective
import org.betonquest.betonquest.api.QuestException
import org.betonquest.betonquest.api.instruction.Instruction
import org.betonquest.betonquest.api.quest.objective.ObjectiveFactory
import org.betonquest.betonquest.api.quest.objective.service.ObjectiveService
import org.bukkit.entity.Player

class CropObjectiveFactory(
    private val action: Action,
    private val notifyMessage: NotifyMessage
) : ObjectiveFactory {
    override fun parseInstruction(instruction: Instruction, service: ObjectiveService): DefaultObjective {
        val id = instruction.string().list().get()
        val targetAmount = instruction.number().get("amount", 1)

        val objective = CropObjective(service, targetAmount, id, notifyMessage)

        return when (action) {
            Action.PLACE -> service.request(CropPlantEvent::class.java)
                .onlineHandler(objective::onPlace)
                .player { it.player }
            Action.BREAK -> service.request(CropBreakEvent::class.java)
                .onlineHandler(objective::onBreak)
                .player { it.entityBreaker() as? Player }
            else -> throw QuestException("Crop objective only supports PLACE or BREAK.")
        }.subscribe(true).let { objective }
    }
}