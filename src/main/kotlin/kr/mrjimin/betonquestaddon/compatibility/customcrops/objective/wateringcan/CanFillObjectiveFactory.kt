package kr.mrjimin.betonquestaddon.compatibility.customcrops.objective.wateringcan

import kr.mrjimin.betonquestaddon.util.parseOptions
import net.momirealms.customcrops.api.event.WateringCanFillEvent
import org.betonquest.betonquest.api.DefaultObjective
import org.betonquest.betonquest.api.instruction.Instruction
import org.betonquest.betonquest.api.quest.objective.ObjectiveFactory
import org.betonquest.betonquest.api.quest.objective.service.ObjectiveService

class CanFillObjectiveFactory : ObjectiveFactory {
    
    override fun parseInstruction(instruction: Instruction, service: ObjectiveService): DefaultObjective {
        val (amount, options) = instruction.parseOptions()
        val objective = CanFillObjective(service, amount, options)
        service.request(WateringCanFillEvent::class.java)
            .onlineHandler(objective::onFillWateringCan)
            .player { it.player }
            .subscribe(true)
        return objective
    }
}