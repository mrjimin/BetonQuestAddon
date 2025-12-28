package com.github.mrjimin.betonquestaddon.compatibility.craftengine.objectives

import com.github.mrjimin.betonquestaddon.util.event.ActionType
import net.momirealms.craftengine.bukkit.api.event.*
import org.betonquest.betonquest.api.instruction.Instruction
import org.betonquest.betonquest.api.instruction.Argument
import org.bukkit.event.EventHandler

class CraftEngineBlockObjective(
    instruction: Instruction,
    amount: Argument<Number>?,
    message: String,
    item: Argument<String>,
    actionType: ActionType,
    isCancelled: Argument<Boolean>?
) : CraftEngineObjective(instruction, message, amount, item, actionType, isCancelled) {

    @EventHandler(ignoreCancelled = true)
    fun CustomBlockBreakEvent.onBreak() {
        handle(ActionType.BREAK, player, bukkitBlock())
    }

    @EventHandler(ignoreCancelled = true)
    fun CustomBlockPlaceEvent.onPlace() {
        handle(ActionType.PLACE, player, bukkitBlock())
    }

    @EventHandler(ignoreCancelled = true)
    fun CustomBlockInteractEvent.onInteract() {
        handle(ActionType.INTERACT, player, bukkitBlock())
    }

}