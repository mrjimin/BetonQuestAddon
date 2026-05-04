package kr.mrjimin.betonquestaddon.compatibility.customcrops.objective.fertilizer

import kr.mrjimin.betonquestaddon.util.parseOptions
import net.momirealms.customcrops.api.event.FertilizerUseEvent
import org.betonquest.betonquest.api.DefaultObjective
import org.betonquest.betonquest.api.instruction.Instruction
import org.betonquest.betonquest.api.quest.objective.ObjectiveFactory
import org.betonquest.betonquest.api.quest.objective.service.ObjectiveService

class FertilizerUseObjectiveFactory : ObjectiveFactory {

    override fun parseInstruction(instruction: Instruction, service: ObjectiveService): DefaultObjective {
        val (amount, baseOptions) = instruction.parseOptions()
        val pots = instruction.string().list().get("pots", listOf())

        val options = baseOptions.copy(
            targetIds = pots
        )

        val objective = FertilizerUseObjective(service, amount, options)
        service.request(FertilizerUseEvent::class.java)
            .onlineHandler(objective::onUseFertilizer)
            .player { it.player }
            .subscribe(true)
        return objective
    }
}