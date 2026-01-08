package com.github.mrjimin.betonquestaddon.compatibility.nexo.objectives

import com.github.mrjimin.betonquestaddon.util.action.ActionType
import com.nexomc.nexo.api.events.custom_block.*
import org.betonquest.betonquest.api.instruction.Instruction
import org.betonquest.betonquest.api.instruction.Argument
import org.bukkit.event.EventHandler

class NexoBlockObjective(
    instruction: Instruction,
    amount: Argument<Number>?,
    message: String,
    item: Argument<String>,
    actionType: ActionType,
    isCancelled: Argument<Boolean>?
) : NexoObjective(instruction, message, amount, item, actionType, isCancelled) {

    @EventHandler(ignoreCancelled = true)
    fun NexoBlockBreakEvent.onBreak() {
        handle(ActionType.BREAK, player, block)
    }

    @EventHandler(ignoreCancelled = true)
    fun NexoBlockPlaceEvent.onPlace() {
        handle(ActionType.PLACE, player, block)
    }

    @EventHandler(ignoreCancelled = true)
    fun NexoBlockInteractEvent.onInteract() {
        handle(ActionType.INTERACT, player, block)
    }
}