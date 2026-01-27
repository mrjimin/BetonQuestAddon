package kr.mrjimin.betonquestaddon.compatibility.customcrops.objective.wateringcan

import net.momirealms.customcrops.api.event.WateringCanWaterSprinklerEvent
import org.betonquest.betonquest.api.DefaultObjective
import org.betonquest.betonquest.api.instruction.Instruction
import org.betonquest.betonquest.api.quest.objective.ObjectiveFactory
import org.betonquest.betonquest.api.quest.objective.service.ObjectiveService

class CanSprinklerObjectiveFactory: ObjectiveFactory {
    override fun parseInstruction(instruction: Instruction, service: ObjectiveService): DefaultObjective {
        val id = instruction.string().list().get()
        val targetAmount = instruction.number().get("amount", 1)
        val targets = instruction.string().list().get("sprinklers", listOf())

        val objective = CanSprinklerObjective(service, targetAmount, id, targets)
        service.request(WateringCanWaterSprinklerEvent::class.java).handler(objective::onWateringSprinkler).subscribe(true)
        return objective
    }
}