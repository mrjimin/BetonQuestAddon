package kr.mrjimin.betonquestaddon.compatibility.customcrops.objective.wateringcan

import kr.mrjimin.betonquestaddon.util.parseOptions
import net.momirealms.customcrops.api.event.WateringCanWaterSprinklerEvent
import org.betonquest.betonquest.api.DefaultObjective
import org.betonquest.betonquest.api.instruction.Instruction
import org.betonquest.betonquest.api.quest.objective.ObjectiveFactory
import org.betonquest.betonquest.api.quest.objective.service.ObjectiveService

class CanSprinklerObjectiveFactory: ObjectiveFactory {
    override fun parseInstruction(instruction: Instruction, service: ObjectiveService): DefaultObjective {
        val (amount, baseOptions) = instruction.parseOptions()
        val sprinklers = instruction.string().list().get("sprinklers").orElse(null)

        val options = baseOptions.copy(
            targetIds = sprinklers
        )

        val objective = CanSprinklerObjective(service, amount, options)
        service.request(WateringCanWaterSprinklerEvent::class.java)
            .onlineHandler(objective::onWateringSprinkler)
            .player { it.player }
            .subscribe(true)
        return objective
    }
}