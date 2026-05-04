package kr.mrjimin.betonquestaddon.betonquest.objective

import kr.mrjimin.betonquestaddon.util.parseOptions
import org.betonquest.betonquest.api.instruction.Instruction
import org.betonquest.betonquest.api.quest.objective.Objective
import org.betonquest.betonquest.api.quest.objective.ObjectiveFactory
import org.betonquest.betonquest.api.quest.objective.service.ObjectiveService
import org.bukkit.entity.Player
import org.bukkit.event.inventory.InventoryClickEvent

class StoneCutObjectiveFactory : ObjectiveFactory {
    override fun parseInstruction(instruction: Instruction, service: ObjectiveService): Objective {
        val (amount, options) = instruction.parseOptions()
        val objective = StoneCutObjective(service, amount, options)
        service.request(InventoryClickEvent::class.java)
            .onlineHandler(objective::onStonCutting)
            .player { it.view.player as Player }
            .subscribe(true)
        return objective
    }
}