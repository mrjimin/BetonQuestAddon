package com.github.mrjimin.betonquestaddon.compatibilityold.nexo.objectives.block

import com.github.mrjimin.betonquestaddon.compatibilityold.LangMessageKey
import com.github.mrjimin.betonquestaddon.compatibilityold.nexo.objectives.NxObjective
import com.nexomc.nexo.api.events.custom_block.NexoBlockInteractEvent
import org.betonquest.betonquest.api.instruction.Instruction
import org.betonquest.betonquest.api.instruction.variable.Variable
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener

class NxBlockInteract(
    instruction: Instruction,
    targetAmount: Variable<Number>,
    itemID: Variable<String>,
    val isCancel: Boolean
) : NxObjective(instruction, targetAmount, LangMessageKey.BLOCK_INTERACT, itemID), Listener {

    @EventHandler
    fun NexoBlockInteractEvent.onNxBlockInteract() {
        isCancelled = isCancel
        handle(player, mechanic.itemID)
    }

}