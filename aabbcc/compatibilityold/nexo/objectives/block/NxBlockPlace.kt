package com.github.mrjimin.betonquestaddon.compatibilityold.nexo.objectives.block

import com.github.mrjimin.betonquestaddon.compatibilityold.LangMessageKey
import com.github.mrjimin.betonquestaddon.compatibilityold.nexo.objectives.NxObjective
import com.nexomc.nexo.api.events.custom_block.NexoBlockPlaceEvent
import org.betonquest.betonquest.api.instruction.Instruction
import org.betonquest.betonquest.api.instruction.variable.Variable
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener

class NxBlockPlace(
    instruction: Instruction,
    targetAmount: Variable<Number>,
    itemID: Variable<String>
) : NxObjective(instruction, targetAmount, LangMessageKey.BLOCK_PLACE, itemID), Listener {

    @EventHandler
    fun NexoBlockPlaceEvent.onNexoBlockPlace() {
        handle(player, mechanic.itemID)
    }
}