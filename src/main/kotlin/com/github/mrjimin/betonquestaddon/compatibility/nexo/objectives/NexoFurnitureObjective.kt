package com.github.mrjimin.betonquestaddon.compatibility.nexo.objectives

import com.github.mrjimin.betonquestaddon.util.event.ActionType
import com.nexomc.nexo.api.events.furniture.*
import org.betonquest.betonquest.api.instruction.Instruction
import org.betonquest.betonquest.api.instruction.variable.Variable
import org.bukkit.event.EventHandler

class NexoFurnitureObjective(
    instruction: Instruction,
    targetAmount: Variable<Number>?,
    message: String,
    item: Variable<String>,
    actionType: ActionType
) : NexoObjective(instruction, targetAmount, message, item, actionType) {

    @EventHandler(ignoreCancelled = true)
    fun NexoFurnitureBreakEvent.onBreak() {
        if (actionType != ActionType.BREAK) return
        handle(player, baseEntity)
    }

    @EventHandler(ignoreCancelled = true)
    fun NexoFurniturePlaceEvent.onPlace() {
        if (actionType != ActionType.PLACE) return
        handle(player, baseEntity)
    }

    @EventHandler(ignoreCancelled = true)
    fun NexoFurnitureInteractEvent.onInteract() {
        if (actionType != ActionType.INTERACT) return
        handle(player, baseEntity)
    }

}
