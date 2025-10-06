package com.github.mrjimin.betonquestaddon.compatibility.itemsadder.objectives.block

import com.github.mrjimin.betonquestaddon.compatibility.LangMessageKey
import com.github.mrjimin.betonquestaddon.compatibility.itemsadder.objectives.IaObjective
import dev.lone.itemsadder.api.CustomStack
import dev.lone.itemsadder.api.Events.CustomBlockBreakEvent
import org.betonquest.betonquest.api.instruction.Instruction
import org.betonquest.betonquest.api.instruction.variable.Variable
import org.bukkit.event.EventHandler

class IaBlockBreak(
    instruction: Instruction,
    targetAmount: Variable<Number>,
    itemID: Variable<CustomStack>
) : IaObjective(instruction, targetAmount, LangMessageKey.BLOCK_BREAK, itemID) {

    @EventHandler
    fun CustomBlockBreakEvent.onBlockBreak() {
        handle(player, namespacedID)
    }
}