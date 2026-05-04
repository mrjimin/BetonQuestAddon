package kr.mrjimin.betonquestaddon.compatibility.customcrops.objective.wateringcan

import kr.mrjimin.betonquestaddon.util.parseOptions
import net.momirealms.customcrops.api.event.WateringCanWaterPotEvent
import org.betonquest.betonquest.api.DefaultObjective
import org.betonquest.betonquest.api.instruction.Instruction
import org.betonquest.betonquest.api.quest.objective.ObjectiveFactory
import org.betonquest.betonquest.api.quest.objective.service.ObjectiveService

class CanPotObjectiveFactory: ObjectiveFactory {
    override fun parseInstruction(instruction: Instruction, service: ObjectiveService): DefaultObjective {
        val (amount, baseOptions) = instruction.parseOptions()
        val pots = instruction.string().list().get("pots", listOf())

        val options = baseOptions.copy(
            targetIds = pots
        )

        val objective = CanPotObjective(service, amount, options)
        service.request(WateringCanWaterPotEvent::class.java)
            .onlineHandler(objective::onWateringPot)
            .player { it.player }
            .subscribe(true)
        return objective
    }
}