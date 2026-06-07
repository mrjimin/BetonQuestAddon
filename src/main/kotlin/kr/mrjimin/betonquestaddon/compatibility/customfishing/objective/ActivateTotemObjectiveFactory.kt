package kr.mrjimin.betonquestaddon.compatibility.customfishing.objective

import kr.mrjimin.betonquestaddon.config.NotifyMessage
import kr.mrjimin.betonquestaddon.util.parseDefaultOptions
import net.momirealms.customfishing.api.event.TotemActivateEvent
import org.betonquest.betonquest.api.DefaultObjective
import org.betonquest.betonquest.api.instruction.Instruction
import org.betonquest.betonquest.api.quest.objective.ObjectiveFactory
import org.betonquest.betonquest.api.quest.objective.service.ObjectiveService
import org.bukkit.event.EventPriority

class ActivateTotemObjectiveFactory : ObjectiveFactory {

    override fun parseInstruction(instruction: Instruction, service: ObjectiveService): DefaultObjective {
        val id = instruction.string().list().get()
        val options = instruction.parseDefaultOptions()
        val objective = ActivateTotemObjective(service, options, NotifyMessage.CUSTOM_FISHING_ACTIVATE_TOTEM, id)

        service.request(TotemActivateEvent::class.java)
            .onlineHandler(objective::onActivateTotem)
            .priority(EventPriority.MONITOR)
            .player { it.player }
            .subscribe(true)
        return objective
    }
}