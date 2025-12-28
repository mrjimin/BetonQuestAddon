package com.github.mrjimin.betonquestaddon.compatibility.itemsadder.objectives

import com.github.mrjimin.betonquestaddon.objectives.AbstractCheckObjective
import com.github.mrjimin.betonquestaddon.util.event.ActionType
import dev.lone.itemsadder.api.CustomBlock
import dev.lone.itemsadder.api.CustomFurniture
import org.betonquest.betonquest.api.instruction.Instruction
import org.betonquest.betonquest.api.instruction.Argument
import org.bukkit.block.Block
import org.bukkit.entity.Entity
import org.bukkit.entity.Player

abstract class ItemsAdderObjective(
    instruction: Instruction,
    message: String,
    amount: Argument<Number>?,
    item: Argument<String>,
    actionType: ActionType,
    isCancelled: Argument<Boolean>?
) : AbstractCheckObjective(instruction, message, amount, item, actionType, isCancelled) {

    protected fun handle(
        expected: ActionType,
        player: Player,
        entity: Entity
    ) {
        handleItem(
            expected,
            player,
            CustomFurniture.byAlreadySpawned(entity)?.namespacedID
        )
    }

    protected fun handle(
        expected: ActionType,
        player: Player,
        block: Block
    ) {
        handleItem(
            expected,
            player,
            CustomBlock.byAlreadyPlaced(block)?.namespacedID
        )
    }
}