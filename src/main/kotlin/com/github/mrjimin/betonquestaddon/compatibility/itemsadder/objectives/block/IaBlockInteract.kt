package com.github.mrjimin.betonquestaddon.compatibility.itemsadder.objectives.block

import com.github.mrjimin.betonquestaddon.compatibility.LangMessageKey
import com.github.mrjimin.betonquestaddon.compatibility.itemsadder.objectives.IaObjective
import dev.lone.itemsadder.api.CustomStack
import dev.lone.itemsadder.api.Events.CustomBlockInteractEvent
import org.betonquest.betonquest.api.instruction.Instruction
import org.betonquest.betonquest.api.instruction.variable.Variable
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener

class IaBlockInteract(
    instruction: Instruction,
    targetAmount: Variable<Number>,
    itemID: Variable<CustomStack>,
    val isCancel: Boolean
) : IaObjective(instruction, targetAmount, LangMessageKey.BLOCK_INTERACT, itemID), Listener {

    @EventHandler
    fun CustomBlockInteractEvent.onCustomBlockInteract() {
        isCancelled = isCancel
        handle(player, namespacedID)
    }

}