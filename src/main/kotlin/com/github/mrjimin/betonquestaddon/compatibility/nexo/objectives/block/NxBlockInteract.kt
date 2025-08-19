package com.github.mrjimin.betonquestaddon.compatibility.nexo.objectives.block

import com.github.mrjimin.betonquestaddon.compatibility.LangMessageKey
import com.github.mrjimin.betonquestaddon.compatibility.nexo.objectives.NxObjective
import com.nexomc.nexo.api.events.custom_block.NexoBlockInteractEvent
import org.betonquest.betonquest.api.logger.BetonQuestLogger
import org.betonquest.betonquest.api.instruction.Instruction
import org.betonquest.betonquest.api.instruction.variable.Variable
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener

class NxBlockInteract(
    instruction: Instruction,
    targetAmount: Variable<Number>,
    log: BetonQuestLogger,
    itemID: Variable<String>,
    val isCancel: Boolean
) : NxObjective(instruction, targetAmount, LangMessageKey.BLOCK_INTERACT, log, itemID), Listener {

    @EventHandler
    fun NexoBlockInteractEvent.onNxBlockInteract() {
        isCancelled = isCancel
        handle(mechanic.itemID, player)
    }

}