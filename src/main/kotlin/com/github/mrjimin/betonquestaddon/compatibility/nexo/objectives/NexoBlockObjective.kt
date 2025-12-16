package com.github.mrjimin.betonquestaddon.compatibility.nexo.objectives

import com.github.mrjimin.betonquestaddon.util.event.ActionType
import com.nexomc.nexo.api.events.custom_block.*
import org.betonquest.betonquest.api.instruction.Instruction
import org.betonquest.betonquest.api.instruction.variable.Variable
import org.bukkit.event.EventHandler

class NexoBlockObjective(
    instruction: Instruction,
    targetAmount: Variable<Number>?,
    message: String,
    item: Variable<String>,
    actionType: ActionType
) : NexoObjective(instruction, targetAmount, message, item, actionType) {

    @EventHandler(ignoreCancelled = true)
    fun NexoBlockBreakEvent.onBreak() {
        if (actionType != ActionType.BREAK) return
        handle(player, block)
    }

    @EventHandler(ignoreCancelled = true)
    fun NexoBlockPlaceEvent.onPlace() {
        if (actionType != ActionType.PLACE) return
        handle(player, block)
    }

    @EventHandler(ignoreCancelled = true)
    fun NexoBlockInteractEvent.onInteract() {
        if (actionType != ActionType.INTERACT) return
        handle(player, block)
    }

}