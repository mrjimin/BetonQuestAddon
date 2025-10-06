package com.github.mrjimin.betonquestaddon.compatibility.nexo.objectives.furniture

import com.github.mrjimin.betonquestaddon.compatibility.LangMessageKey
import com.github.mrjimin.betonquestaddon.compatibility.nexo.objectives.NxObjective
import com.nexomc.nexo.api.NexoFurniture
import com.nexomc.nexo.api.events.furniture.NexoFurniturePlaceEvent
import org.betonquest.betonquest.api.instruction.Instruction
import org.betonquest.betonquest.api.instruction.variable.Variable
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener

class NxFurniturePlace(
    instruction: Instruction,
    targetAmount: Variable<Number>,
    itemID: Variable<String>
) : NxObjective(instruction, targetAmount, LangMessageKey.FURNITURE_PLACE, itemID), Listener {

    @EventHandler
    fun NexoFurniturePlaceEvent.onCeFurniturePlace() {
        val id = NexoFurniture.furnitureMechanic(baseEntity)?.itemID
        handle(player, id)
    }
}
