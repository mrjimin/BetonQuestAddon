package com.github.mrjimin.betonquestaddon.compatibility.nexo.objectives

import com.github.mrjimin.betonquestaddon.objectives.AbstractCheckObjective
import com.github.mrjimin.betonquestaddon.util.event.ActionType
import com.nexomc.nexo.api.NexoBlocks
import com.nexomc.nexo.api.NexoFurniture
import org.betonquest.betonquest.api.instruction.Instruction
import org.betonquest.betonquest.api.instruction.Argument
import org.bukkit.block.Block
import org.bukkit.entity.ItemDisplay
import org.bukkit.entity.Player

abstract class NexoObjective(
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
        entity: ItemDisplay
    ) {
        handleItem(
            expected,
            player,
            NexoFurniture
                .furnitureMechanic(entity)
                ?.itemID
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
            NexoBlocks
                .customBlockMechanic(block)
                ?.itemID
        )
    }
}
