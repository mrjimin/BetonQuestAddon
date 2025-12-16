package com.github.mrjimin.betonquestaddon.compatibility.nexo.objectives

import com.github.mrjimin.betonquestaddon.util.event.ActionType
import com.nexomc.nexo.api.NexoBlocks
import com.nexomc.nexo.api.NexoFurniture
import org.betonquest.betonquest.api.CountingObjective
import org.betonquest.betonquest.api.instruction.Instruction
import org.betonquest.betonquest.api.instruction.variable.Variable
import org.bukkit.block.Block
import org.bukkit.entity.ItemDisplay
import org.bukkit.entity.Player
import org.bukkit.event.Listener

abstract class NexoObjective(
    instruction: Instruction,
    targetAmount: Variable<Number>?,
    message: String,
    protected val item: Variable<String>,
    protected val actionType: ActionType
) : CountingObjective(instruction, targetAmount, message), Listener {

    protected fun handle(player: Player, baseEntity: ItemDisplay) {
        val itemId = NexoFurniture.furnitureMechanic(baseEntity)?.itemID ?: return
        processObjective(player, itemId)
    }

    protected fun handle(player: Player, block: Block) {
        val itemId = NexoBlocks.customBlockMechanic(block)?.itemID ?: return
        processObjective(player, itemId)
    }

    private fun processObjective(player: Player, itemId: String) {
        val profile = profileProvider.getProfile(player)
        qeHandler.handle {
            if (
                containsPlayer(profile) &&
                item.getValue(profile) == itemId &&
                checkConditions(profile)
            ) {
                getCountingData(profile).progress()
                completeIfDoneOrNotify(profile)
            }
        }
    }
}

