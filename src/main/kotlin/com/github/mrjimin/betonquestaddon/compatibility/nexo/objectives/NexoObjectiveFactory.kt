package com.github.mrjimin.betonquestaddon.compatibility.nexo.objectives

import com.github.mrjimin.betonquestaddon.util.action.ActionType
import com.nexomc.nexo.api.events.custom_block.*
import com.nexomc.nexo.api.events.furniture.*
import org.betonquest.betonquest.api.DefaultObjective
import org.betonquest.betonquest.api.instruction.Instruction
import org.betonquest.betonquest.api.quest.objective.ObjectiveFactory
import org.betonquest.betonquest.api.quest.objective.event.ObjectiveFactoryService

class NexoObjectiveFactory(
    private val actionType: ActionType
) : ObjectiveFactory {

    override fun parseInstruction(instruction: Instruction, service: ObjectiveFactoryService): DefaultObjective {
        val identifier = instruction.string().get()
        val targetAmount = instruction.number().get("amount", 1)

        val objective = NexoObjective(service, targetAmount, actionType, identifier)

        return when (actionType) {
            ActionType.PLACE_BLOCK -> service.request(NexoBlockPlaceEvent::class.java).handler(objective::onBlockPlace)
            ActionType.BREAK_BLOCK -> service.request(NexoBlockBreakEvent::class.java).handler(objective::onBlockBreak)
            ActionType.INTERACT_BLOCK -> service.request(NexoBlockInteractEvent::class.java).handler(objective::onBlockInteract)
            ActionType.PLACE_FURNITURE -> service.request(NexoFurniturePlaceEvent::class.java).handler(objective::onFurniturePlace)
            ActionType.BREAK_FURNITURE -> service.request(NexoFurnitureBreakEvent::class.java).handler(objective::onFurnitureBreak)
            ActionType.INTERACT_FURNITURE -> service.request(NexoFurnitureInteractEvent::class.java).handler(objective::onFurnitureInteract)
        }.subscribe(true).let { objective }
    }
}