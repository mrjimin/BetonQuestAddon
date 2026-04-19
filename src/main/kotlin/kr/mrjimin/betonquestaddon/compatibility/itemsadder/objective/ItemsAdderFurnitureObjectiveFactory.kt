package kr.mrjimin.betonquestaddon.compatibility.itemsadder.objective

import dev.lone.itemsadder.api.Events.FurnitureBreakEvent
import dev.lone.itemsadder.api.Events.FurnitureInteractEvent
import dev.lone.itemsadder.api.Events.FurniturePlacedEvent
import kr.mrjimin.betonquestaddon.betonquest.objective.AbstractAddonObjectiveFactory
import kr.mrjimin.betonquestaddon.config.NotifyMessage
import kr.mrjimin.betonquestaddon.util.action.Action
import org.betonquest.betonquest.api.instruction.Instruction
import org.betonquest.betonquest.api.quest.objective.Objective
import org.betonquest.betonquest.api.quest.objective.service.ObjectiveService

class ItemsAdderFurnitureObjectiveFactory(
    action: Action,
    notifyMessage: NotifyMessage
) : AbstractAddonObjectiveFactory(action, notifyMessage) {

    override fun parseInstruction(instruction: Instruction, service: ObjectiveService): Objective {
        val args = parseBaseArgs(instruction)
        val objective = ItemsAdderFurnitureObjective(service, args.amount, args.ids, args.isCancelled, args.location, args.range, notifyMessage)

        return when (action) {
            Action.PLACE -> service.request(FurniturePlacedEvent::class.java)
                .onlineHandler(objective::onPlace)
                .player { it.player }
            Action.BREAK -> service.request(FurnitureBreakEvent::class.java)
                .onlineHandler(objective::onBreak)
                .player { it.player }
            Action.INTERACT -> service.request(FurnitureInteractEvent::class.java)
                .onlineHandler(objective::onInteract)
                .player { it.player }
        }.subscribe(true).let { objective }
    }
}