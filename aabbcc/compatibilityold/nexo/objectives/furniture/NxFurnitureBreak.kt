package com.github.mrjimin.betonquestaddon.compatibilityold.nexo.objectives.furniture

import com.github.mrjimin.betonquestaddon.compatibilityold.LangMessageKey
import com.github.mrjimin.betonquestaddon.compatibilityold.nexo.objectives.NxObjective
import com.nexomc.nexo.api.NexoFurniture
import com.nexomc.nexo.api.events.furniture.NexoFurnitureBreakEvent
import org.betonquest.betonquest.api.instruction.Instruction
import org.betonquest.betonquest.api.instruction.variable.Variable
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener

class NxFurnitureBreak(
    instruction: Instruction,
    targetAmount: Variable<Number>,
    itemID: Variable<String>
) : NxObjective(instruction, targetAmount, LangMessageKey.FURNITURE_BREAK, itemID), Listener {

    @EventHandler
    fun NexoFurnitureBreakEvent.onNxFurnitureBreak() {
        val id = NexoFurniture.furnitureMechanic(baseEntity)?.itemID
        println(id)
        handle(player, id)
    }
}