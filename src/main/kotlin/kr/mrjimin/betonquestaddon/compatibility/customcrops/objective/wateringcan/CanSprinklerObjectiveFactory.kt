package kr.mrjimin.betonquestaddon.compatibility.customcrops.objective.wateringcan

import kr.mrjimin.betonquestaddon.config.NotifyMessage
import kr.mrjimin.betonquestaddon.util.parseDefaultOptions
import net.momirealms.customcrops.api.event.WateringCanWaterSprinklerEvent
import org.betonquest.betonquest.api.DefaultObjective
import org.betonquest.betonquest.api.instruction.Instruction
import org.betonquest.betonquest.api.quest.objective.ObjectiveFactory
import org.betonquest.betonquest.api.quest.objective.service.ObjectiveService
import org.bukkit.event.EventPriority

class CanSprinklerObjectiveFactory: ObjectiveFactory {
    override fun parseInstruction(instruction: Instruction, service: ObjectiveService): DefaultObjective {
        val id = instruction.string().list().get()
        val options = instruction.parseDefaultOptions()
        val sprinklers = instruction.string().list().get("sprinklers").orElse(null)

        val objective = CanSprinklerObjective(service, options, NotifyMessage.CUSTOM_CROPS_CAN_SPRINKLER, id, sprinklers)
        service.request(WateringCanWaterSprinklerEvent::class.java)
            .onlineHandler(objective::onWateringSprinkler)
            .priority(EventPriority.MONITOR)
            .player { it.player }
            .subscribe(true)
        return objective
    }
}