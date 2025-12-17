package com.github.mrjimin.betonquestaddon.compatibility.craftengine.objectives

import com.github.mrjimin.betonquestaddon.objectives.AbstractCheckObjective
import com.github.mrjimin.betonquestaddon.util.event.ActionType
import net.momirealms.craftengine.bukkit.api.BukkitAdaptors
import net.momirealms.craftengine.bukkit.api.CraftEngineFurniture
import org.betonquest.betonquest.api.instruction.Instruction
import org.betonquest.betonquest.api.instruction.variable.Variable
import org.bukkit.block.Block
import org.bukkit.entity.Entity
import org.bukkit.entity.ItemDisplay
import org.bukkit.entity.Player

abstract class CraftEngineObjective(
    instruction: Instruction,
    message: String,
    amount: Variable<Number>?,
    item: Variable<String>,
    actionType: ActionType,
    isCancelled: Variable<Boolean>?
) : AbstractCheckObjective(instruction, message, amount, item, actionType, isCancelled) {

    protected fun handle(
        expected: ActionType,
        player: Player,
        entity: Entity
    ) {
        handleItem(
            expected,
            player,
            CraftEngineFurniture
                .getLoadedFurnitureByMetaEntity(entity)
                ?.id()
                ?.toString()
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
            BukkitAdaptors
                .adapt(block)
                .id()
                .toString()
        )
    }
}
