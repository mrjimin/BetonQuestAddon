package com.github.mrjimin.betonquestaddon.compatibility.craftengine.objectives

import com.github.mrjimin.betonquestaddon.util.event.ActionType
import net.momirealms.craftengine.bukkit.api.event.*
import org.betonquest.betonquest.api.instruction.Instruction
import org.betonquest.betonquest.api.instruction.Argument
import org.bukkit.event.EventHandler

class CraftEngineFurnitureObjective(
    instruction: Instruction,
    amount: Argument<Number>?,
    message: String,
    item: Argument<String>,
    actionType: ActionType,
    isCancelled: Argument<Boolean>?
) : CraftEngineObjective(instruction, message, amount, item, actionType, isCancelled) {

    @EventHandler(ignoreCancelled = true)
    fun FurnitureBreakEvent.onBreak() {
        handle(ActionType.BREAK, player, furniture().bukkitEntity)
    }

    @EventHandler(ignoreCancelled = true)
    fun FurniturePlaceEvent.onPlace() {
        handle(ActionType.PLACE, player, furniture().bukkitEntity)
    }

    @EventHandler(ignoreCancelled = true)
    fun FurnitureInteractEvent.onInteract() {
        handle(ActionType.INTERACT, player, furniture().bukkitEntity)
    }

}