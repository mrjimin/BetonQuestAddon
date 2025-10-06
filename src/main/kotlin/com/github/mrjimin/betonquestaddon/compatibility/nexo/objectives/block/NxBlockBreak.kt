package com.github.mrjimin.betonquestaddon.compatibility.nexo.objectives.block

import com.github.mrjimin.betonquestaddon.compatibility.LangMessageKey
import com.github.mrjimin.betonquestaddon.compatibility.nexo.objectives.NxObjective
import com.nexomc.nexo.api.events.custom_block.NexoBlockBreakEvent
import org.betonquest.betonquest.api.instruction.Instruction
import org.betonquest.betonquest.api.instruction.variable.Variable
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener

class NxBlockBreak(
    instruction: Instruction,
    targetAmount: Variable<Number>,
    itemID: Variable<String>
) : NxObjective(instruction, targetAmount, LangMessageKey.BLOCK_BREAK, itemID), Listener {

    @EventHandler
    fun NexoBlockBreakEvent.onNexoBlockBreak() {
        handle(player, mechanic.itemID)
    }
}