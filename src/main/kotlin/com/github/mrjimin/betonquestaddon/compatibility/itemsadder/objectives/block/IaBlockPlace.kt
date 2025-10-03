package com.github.mrjimin.betonquestaddon.compatibility.itemsadder.objectives.block

import com.github.mrjimin.betonquestaddon.compatibility.LangMessageKey
import com.github.mrjimin.betonquestaddon.compatibility.itemsadder.objectives.IaObjective
import dev.lone.itemsadder.api.CustomStack
import dev.lone.itemsadder.api.Events.CustomBlockPlaceEvent
import org.betonquest.betonquest.api.logger.BetonQuestLogger
import org.betonquest.betonquest.api.instruction.Instruction
import org.betonquest.betonquest.api.instruction.variable.Variable
import org.bukkit.event.EventHandler

class IaBlockPlace(
    instruction: Instruction,
    targetAmount: Variable<Number>,
    itemID: Variable<CustomStack>
) : IaObjective(instruction, targetAmount, LangMessageKey.BLOCK_PLACE, itemID) {

    @EventHandler
    fun CustomBlockPlaceEvent.onBlockPlace() {
        handle(namespacedID, player)
    }
}