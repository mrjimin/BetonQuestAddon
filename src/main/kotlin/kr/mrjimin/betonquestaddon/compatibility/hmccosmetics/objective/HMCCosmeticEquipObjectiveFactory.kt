package kr.mrjimin.betonquestaddon.compatibility.hmccosmetics.objective

import com.hibiscusmc.hmccosmetics.api.events.PlayerCosmeticEquipEvent
import kr.mrjimin.betonquestaddon.util.parseDefaultOptions
import org.betonquest.betonquest.api.instruction.Instruction
import org.betonquest.betonquest.api.quest.objective.Objective
import org.betonquest.betonquest.api.quest.objective.ObjectiveFactory
import org.betonquest.betonquest.api.quest.objective.service.ObjectiveService
import org.bukkit.event.EventPriority

class HMCCosmeticEquipObjectiveFactory : ObjectiveFactory {

    override fun parseInstruction(instruction: Instruction, service: ObjectiveService): Objective {
        val id = instruction.string().get()
        val options = instruction.parseDefaultOptions()
        val objective = HMCCosmeticEquipObjective(service, options, id)
        service.request(PlayerCosmeticEquipEvent::class.java)
            .onlineHandler(objective::onCosmeticEquip)
            .priority(EventPriority.MONITOR)
            .player { it.user.player }
            .subscribe(true)
        return objective
    }

}