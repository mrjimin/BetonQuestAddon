package kr.mrjimin.betonquestaddon.compatibility.nexo.objective

import com.nexomc.nexo.api.events.furniture.NexoFurnitureBreakEvent
import com.nexomc.nexo.api.events.furniture.NexoFurnitureInteractEvent
import com.nexomc.nexo.api.events.furniture.NexoFurniturePlaceEvent
import kr.mrjimin.betonquestaddon.config.NotifyMessage
import kr.mrjimin.betonquestaddon.util.action.Action
import kr.mrjimin.betonquestaddon.util.parseDefaultOptions
import org.betonquest.betonquest.api.DefaultObjective
import org.betonquest.betonquest.api.instruction.Instruction
import org.betonquest.betonquest.api.quest.objective.ObjectiveFactory
import org.betonquest.betonquest.api.quest.objective.service.ObjectiveService
import org.bukkit.event.EventPriority

class NexoFurnitureObjectiveFactory(
    private val action: Action,
    private val notifyMessage: NotifyMessage
) : ObjectiveFactory {

    override fun parseInstruction(instruction: Instruction, service: ObjectiveService): DefaultObjective {
        val id = instruction.string().list().get()
        val options = instruction.parseDefaultOptions()
        val objective = NexoFurnitureObjective(service, options, notifyMessage, id)

        return when (action) {
            Action.PLACE -> service.request(NexoFurniturePlaceEvent::class.java)
                .onlineHandler(objective::onPlace)
                .priority(EventPriority.MONITOR)
                .player { it.player }

            Action.BREAK -> service.request(NexoFurnitureBreakEvent::class.java)
                .onlineHandler(objective::onBreak)
                .priority(EventPriority.MONITOR)
                .player { it.player }

            Action.INTERACT -> service.request(NexoFurnitureInteractEvent::class.java)
                .onlineHandler(objective::onInteract)
                .priority(EventPriority.MONITOR)
                .player { it.player }
        }.subscribe(true).let { objective }
    }
}