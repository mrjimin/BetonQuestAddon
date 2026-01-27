package kr.mrjimin.betonquestaddon.compatibility.customcrops.objective.fertilizer

import net.momirealms.customcrops.api.event.FertilizerUseEvent
import org.betonquest.betonquest.api.DefaultObjective
import org.betonquest.betonquest.api.instruction.Instruction
import org.betonquest.betonquest.api.quest.objective.ObjectiveFactory
import org.betonquest.betonquest.api.quest.objective.service.ObjectiveService

class FertilizerUseObjectiveFactory : ObjectiveFactory {
    override fun parseInstruction(instruction: Instruction, service: ObjectiveService): DefaultObjective {
        val id = instruction.string().list().get()
        val targetAmount = instruction.number().get("amount", 1)
        val targets = instruction.string().list().get("pots", listOf())

        val objective = FertilizerUseObjective(service, targetAmount, id, targets)
        service.request(FertilizerUseEvent::class.java).handler(objective::onUseFertilizer).subscribe(true)
        return objective
    }
}