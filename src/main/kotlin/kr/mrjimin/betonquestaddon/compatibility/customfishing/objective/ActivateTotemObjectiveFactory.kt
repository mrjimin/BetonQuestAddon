package kr.mrjimin.betonquestaddon.compatibility.customfishing.objective

import kr.mrjimin.betonquestaddon.util.parseOptions
import net.momirealms.customfishing.api.event.TotemActivateEvent
import org.betonquest.betonquest.api.DefaultObjective
import org.betonquest.betonquest.api.instruction.Instruction
import org.betonquest.betonquest.api.quest.objective.ObjectiveFactory
import org.betonquest.betonquest.api.quest.objective.service.ObjectiveService

class ActivateTotemObjectiveFactory : ObjectiveFactory {

    override fun parseInstruction(instruction: Instruction, service: ObjectiveService): DefaultObjective {
        val (amount, options) = instruction.parseOptions()
        val objective = ActivateTotemObjective(service, amount, options)

        service.request(TotemActivateEvent::class.java)
            .onlineHandler(objective::onActivateTotem)
            .player { it.player }
            .subscribe(true)
        return objective
    }
}