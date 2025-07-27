package com.github.mrjimin.betonquestaddon.compatibility.nexo.objectives.furniture

import com.github.mrjimin.betonquestaddon.compatibility.LangMessageKey
import com.github.mrjimin.betonquestaddon.compatibility.nexo.objectives.NxObjective
import com.nexomc.nexo.api.NexoFurniture
import com.nexomc.nexo.api.events.furniture.NexoFurnitureInteractEvent
import org.betonquest.betonquest.api.logger.BetonQuestLogger
import org.betonquest.betonquest.instruction.Instruction
import org.betonquest.betonquest.instruction.variable.Variable
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener

class NxFurnitureInteract(
    instruction: Instruction,
    targetAmount: Variable<Number>,
    log: BetonQuestLogger,
    itemID: Variable<String>,
    val isCancel: Boolean
) : NxObjective(instruction, targetAmount, LangMessageKey.FURNITURE_INTERACT, log, itemID), Listener {

    @EventHandler
    fun NexoFurnitureInteractEvent.onNxFurnitureInteract() {
        isCancelled = isCancel
        val id = NexoFurniture.furnitureMechanic(baseEntity)?.itemID
        handle(id, player)
    }

}