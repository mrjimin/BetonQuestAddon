package com.github.mrjimin.betonquestaddon.compatibility.nexo.objectives

import com.github.mrjimin.betonquestaddon.util.event.ActionType
import com.nexomc.nexo.api.events.furniture.*
import org.betonquest.betonquest.api.instruction.Instruction
import org.betonquest.betonquest.api.instruction.Argument
import org.bukkit.event.EventHandler

class NexoFurnitureObjective(
    instruction: Instruction,
    amount: Argument<Number>?,
    message: String,
    item: Argument<String>,
    actionType: ActionType,
    isCancelled: Argument<Boolean>?
) : NexoObjective(instruction, message, amount, item, actionType, isCancelled) {

    @EventHandler(ignoreCancelled = true)
    fun NexoFurnitureBreakEvent.onBreak() {
        handle(ActionType.BREAK, player, baseEntity)
    }

    @EventHandler(ignoreCancelled = true)
    fun NexoFurniturePlaceEvent.onPlace() {
        handle(ActionType.PLACE, player, baseEntity)
    }

    @EventHandler(ignoreCancelled = true)
    fun NexoFurnitureInteractEvent.onInteract() {
        handle(ActionType.INTERACT, player, baseEntity)
    }
}
