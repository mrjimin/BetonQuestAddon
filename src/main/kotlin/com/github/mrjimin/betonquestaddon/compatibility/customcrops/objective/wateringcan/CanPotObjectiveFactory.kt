package com.github.mrjimin.betonquestaddon.compatibility.customcrops.objective.wateringcan

import net.momirealms.customcrops.api.event.WateringCanWaterPotEvent
import org.betonquest.betonquest.api.DefaultObjective
import org.betonquest.betonquest.api.instruction.Instruction
import org.betonquest.betonquest.api.quest.objective.ObjectiveFactory
import org.betonquest.betonquest.api.quest.objective.service.ObjectiveService

class CanPotObjectiveFactory: ObjectiveFactory {
    override fun parseInstruction(instruction: Instruction, service: ObjectiveService): DefaultObjective {
        val id = instruction.string().list().get()
        val targetAmount = instruction.number().get("amount", 1)
        val targets = instruction.string().list().get("pots", listOf())

        val objective = CanPotObjective(service, targetAmount, id, targets)
        service.request(WateringCanWaterPotEvent::class.java).handler(objective::onWateringPot).subscribe(true)
        return objective
    }
}