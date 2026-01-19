package com.github.mrjimin.betonquestaddon.compatibility.nexo.objective

import com.github.mrjimin.betonquestaddon.betonquest.objective.AbstractAddonObjectiveFactory
import com.github.mrjimin.betonquestaddon.config.NotifyMessage
import com.github.mrjimin.betonquestaddon.util.action.Action
import com.nexomc.nexo.api.events.furniture.NexoFurnitureBreakEvent
import com.nexomc.nexo.api.events.furniture.NexoFurnitureInteractEvent
import com.nexomc.nexo.api.events.furniture.NexoFurniturePlaceEvent
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
            Action.PLACE -> service.request(NexoFurniturePlaceEvent::class.java).handler(objective::onPlace)
            Action.BREAK -> service.request(NexoFurnitureBreakEvent::class.java).handler(objective::onBreak)
            Action.INTERACT -> service.request(NexoFurnitureInteractEvent::class.java).handler(objective::onInteract)
        }.subscribe(true).let { objective }
    }
}