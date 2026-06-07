package kr.mrjimin.betonquestaddon.compatibility.customcrops.objective.fertilizer

import kr.mrjimin.betonquestaddon.config.NotifyMessage
import kr.mrjimin.betonquestaddon.util.parseDefaultOptions
import net.momirealms.customcrops.api.event.FertilizerUseEvent
import org.betonquest.betonquest.api.DefaultObjective
import org.betonquest.betonquest.api.instruction.Instruction
import org.betonquest.betonquest.api.quest.objective.ObjectiveFactory
import org.betonquest.betonquest.api.quest.objective.service.ObjectiveService
import org.bukkit.event.EventPriority

class FertilizerUseObjectiveFactory : ObjectiveFactory {

    override fun parseInstruction(instruction: Instruction, service: ObjectiveService): DefaultObjective {
        val id = instruction.string().list().get()
        val options = instruction.parseDefaultOptions()
        val pots = instruction.string().list().get("pots").orElse(null)

        val objective = FertilizerUseObjective(service, options, NotifyMessage.CUSTOM_CROPS_USE_FERTILIZER, id, pots)
        service.request(FertilizerUseEvent::class.java)
            .onlineHandler(objective::onUseFertilizer)
            .priority(EventPriority.MONITOR)
            .player { it.player }
            .subscribe(true)
        return objective
    }
}