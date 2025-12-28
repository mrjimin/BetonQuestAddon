package com.github.mrjimin.betonquestaddon.compatibility.itemsadder.objectives

import com.github.mrjimin.betonquestaddon.util.event.ActionType
import dev.lone.itemsadder.api.Events.*
import org.betonquest.betonquest.api.instruction.Instruction
import org.betonquest.betonquest.api.instruction.Argument
import org.bukkit.event.EventHandler

class ItemsAdderBlockObjective(
    instruction: Instruction,
    amount: Argument<Number>?,
    message: String,
    item: Argument<String>,
    actionType: ActionType,
    isCancelled: Argument<Boolean>?
) : ItemsAdderObjective(instruction, message, amount, item, actionType, isCancelled) {

    @EventHandler(ignoreCancelled = true)
    fun CustomBlockBreakEvent.onBreak() {
        handle(ActionType.BREAK, player, block)
    }

    @EventHandler(ignoreCancelled = true)
    fun CustomBlockPlaceEvent.onPlace() {
        handle(ActionType.PLACE, player, block)
    }

    @EventHandler(ignoreCancelled = true)
    fun CustomBlockInteractEvent.onInteract() {
        handle(ActionType.INTERACT, player, blockClicked)
    }
}