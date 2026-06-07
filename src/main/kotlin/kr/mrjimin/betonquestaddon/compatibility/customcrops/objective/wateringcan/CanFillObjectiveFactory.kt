package kr.mrjimin.betonquestaddon.compatibility.customcrops.objective.wateringcan

import kr.mrjimin.betonquestaddon.config.NotifyMessage
import kr.mrjimin.betonquestaddon.util.parseDefaultOptions
import net.momirealms.customcrops.api.event.WateringCanFillEvent
import org.betonquest.betonquest.api.DefaultObjective
import org.betonquest.betonquest.api.instruction.Instruction
import org.betonquest.betonquest.api.quest.objective.ObjectiveFactory
import org.betonquest.betonquest.api.quest.objective.service.ObjectiveService
import org.bukkit.event.EventPriority

class CanFillObjectiveFactory : ObjectiveFactory {
    
    override fun parseInstruction(instruction: Instruction, service: ObjectiveService): DefaultObjective {
        val id = instruction.string().list().get()
        val options = instruction.parseDefaultOptions()
        val objective = CanFillObjective(service, options, NotifyMessage.CUSTOM_CROPS_CAN_FILL, id)
        service.request(WateringCanFillEvent::class.java)
            .onlineHandler(objective::onFillWateringCan)
            .priority(EventPriority.MONITOR)
            .player { it.player }
            .subscribe(true)
        return objective
    }
}