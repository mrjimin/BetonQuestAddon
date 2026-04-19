package kr.mrjimin.betonquestaddon.compatibility.nexo.objective

import com.nexomc.nexo.api.events.furniture.NexoFurnitureBreakEvent
import com.nexomc.nexo.api.events.furniture.NexoFurnitureInteractEvent
import com.nexomc.nexo.api.events.furniture.NexoFurniturePlaceEvent
import kr.mrjimin.betonquestaddon.betonquest.objective.AbstractAddonObjectiveFactory
import kr.mrjimin.betonquestaddon.config.NotifyMessage
import kr.mrjimin.betonquestaddon.util.action.Action
import org.betonquest.betonquest.api.DefaultObjective
import org.betonquest.betonquest.api.instruction.Instruction
import org.betonquest.betonquest.api.quest.objective.service.ObjectiveService

class NexoFurnitureObjectiveFactory(
    action: Action,
    notifyMessage: NotifyMessage
) : AbstractAddonObjectiveFactory(action, notifyMessage) {

    override fun parseInstruction(instruction: Instruction, service: ObjectiveService): DefaultObjective {
        val args = parseBaseArgs(instruction)
        val objective = NexoFurnitureObjective(service, args.amount, args.ids, args.isCancelled, args.location, args.range, notifyMessage)

        return when (action) {
            Action.PLACE -> service.request(NexoFurniturePlaceEvent::class.java)
                .onlineHandler(objective::onPlace)
                .player { it.player }
            Action.BREAK -> service.request(NexoFurnitureBreakEvent::class.java)
                .onlineHandler(objective::onBreak)
                .player { it.player }
            Action.INTERACT -> service.request(NexoFurnitureInteractEvent::class.java)
                .onlineHandler(objective::onInteract)
                .player { it.player }
        }.subscribe(true).let { objective }
    }
}