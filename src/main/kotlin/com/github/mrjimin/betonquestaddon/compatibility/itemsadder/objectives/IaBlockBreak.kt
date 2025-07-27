package com.github.mrjimin.betonquestaddon.compatibility.itemsadder.objectives

import com.github.mrjimin.betonquestaddon.compatibility.LangMessageKey
import dev.lone.itemsadder.api.CustomStack
import dev.lone.itemsadder.api.Events.CustomBlockBreakEvent
import org.betonquest.betonquest.api.logger.BetonQuestLogger
import org.betonquest.betonquest.instruction.Instruction
import org.betonquest.betonquest.instruction.variable.Variable
import org.bukkit.event.EventHandler

class IaBlockBreak(
    instruction: Instruction,
    targetAmount: Variable<Number>,
    log: BetonQuestLogger,
    itemID: Variable<CustomStack>
) : IaBlockObjective(instruction, targetAmount, LangMessageKey.BLOCK_BREAK, log, itemID) {

    @EventHandler
    fun CustomBlockBreakEvent.onBlockBreak() {
        handle(namespacedID, player)
    }
}