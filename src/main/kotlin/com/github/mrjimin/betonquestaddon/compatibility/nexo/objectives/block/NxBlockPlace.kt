package com.github.mrjimin.betonquestaddon.compatibility.nexo.objectives.block

import com.github.mrjimin.betonquestaddon.compatibility.LangMessageKey
import com.github.mrjimin.betonquestaddon.compatibility.nexo.objectives.NxObjective
import com.nexomc.nexo.api.events.custom_block.NexoBlockPlaceEvent
import org.betonquest.betonquest.api.logger.BetonQuestLogger
import org.betonquest.betonquest.instruction.Instruction
import org.betonquest.betonquest.instruction.variable.Variable
import org.bukkit.event.EventHandler
import org.bukkit.event.EventPriority
import org.bukkit.event.Listener

class NxBlockPlace(
    instruction: Instruction,
    targetAmount: Variable<Number>,
    log: BetonQuestLogger,
    itemID: Variable<String>
) : NxObjective(instruction, targetAmount, LangMessageKey.BLOCK_PLACE, log, itemID), Listener {

    @EventHandler(ignoreCancelled = true, priority = EventPriority.MONITOR)
    fun NexoBlockPlaceEvent.onNexoBlockPlace() {
        handle(mechanic.itemID, player)
    }
}