package kr.mrjimin.betonquestaddon.betonquest.objective.smith

import kr.mrjimin.betonquestaddon.util.parseDefaultOptions
import org.betonquest.betonquest.api.instruction.Instruction
import org.betonquest.betonquest.api.quest.objective.Objective
import org.betonquest.betonquest.api.quest.objective.ObjectiveFactory
import org.betonquest.betonquest.api.quest.objective.service.ObjectiveService
import org.bukkit.entity.Player
import org.bukkit.event.EventPriority
import org.bukkit.event.inventory.SmithItemEvent

class SmithObjectiveFactory : ObjectiveFactory {
    override fun parseInstruction(instruction: Instruction, service: ObjectiveService): Objective {
        val item = instruction.item().get()
        val options = instruction.parseDefaultOptions()
        val objective = SmithObjective(service, options, item)

        service.request(SmithItemEvent::class.java)
            .onlineHandler(objective::onSmith)
            .priority(EventPriority.MONITOR)
            .player { it.view.player as Player }
            .subscribe(true)

        return objective
    }
}