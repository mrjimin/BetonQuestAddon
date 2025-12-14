package com.github.mrjimin.betonquestaddon.compatibilityold.itemsadder.objectives.block

import com.github.mrjimin.betonquestaddon.compatibilityold.LangMessageKey
import com.github.mrjimin.betonquestaddon.compatibilityold.itemsadder.objectives.IaObjective
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