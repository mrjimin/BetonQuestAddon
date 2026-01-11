package com.github.mrjimin.betonquestaddon.compatibility.itemsadder.objective

import com.github.mrjimin.betonquestaddon.config.NotifyMessage
import com.github.mrjimin.betonquestaddon.util.action.Action
import dev.lone.itemsadder.api.Events.FurnitureBreakEvent
import dev.lone.itemsadder.api.Events.FurnitureInteractEvent
import dev.lone.itemsadder.api.Events.FurniturePlacedEvent
import org.betonquest.betonquest.api.DefaultObjective
import org.betonquest.betonquest.api.instruction.Instruction
import org.betonquest.betonquest.api.quest.objective.ObjectiveFactory
import org.betonquest.betonquest.api.quest.objective.service.ObjectiveService

class ItemsAdderFurnitureObjectiveFactory(
    private val action: Action,
    private val notifyMessage: NotifyMessage
) : ObjectiveFactory {
    override fun parseInstruction(instruction: Instruction, service: ObjectiveService): DefaultObjective {
        // val id = instruction.string().get()
        val id = instruction.string().list().get()
        val targetAmount = instruction.number().get("amount", 1)

        val objective = ItemsAdderFurnitureObjective(service, targetAmount, id, notifyMessage)

        return when (action) {
            Action.PLACE -> service.request(FurniturePlacedEvent::class.java).handler(objective::onPlace)
            Action.BREAK -> service.request(FurnitureBreakEvent::class.java).handler(objective::onBreak)
            Action.INTERACT -> service.request(FurnitureInteractEvent::class.java).handler(objective::onInteract)
        }.subscribe(true).let { objective }
    }
}