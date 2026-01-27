package kr.mrjimin.betonquestaddon.compatibility.customcrops.objective.wateringcan

import net.momirealms.customcrops.api.event.WateringCanFillEvent
import org.betonquest.betonquest.api.DefaultObjective
import org.betonquest.betonquest.api.instruction.Instruction
import org.betonquest.betonquest.api.quest.objective.ObjectiveFactory
import org.betonquest.betonquest.api.quest.objective.service.ObjectiveService

class CanFillObjectiveFactory : ObjectiveFactory {
    override fun parseInstruction(instruction: Instruction, service: ObjectiveService): DefaultObjective {
        val id = instruction.string().list().get()
        val targetAmount = instruction.number().get("amount", 1)

        val objective = CanFillObjective(service, targetAmount, id)
        service.request(WateringCanFillEvent::class.java).handler(objective::onFillWateringCan).subscribe(true)
        return objective
    }
}