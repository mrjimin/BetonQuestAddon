package com.github.mrjimin.betonquestaddon.compatibility.craftengine.objectives.furniture

import com.github.mrjimin.betonquestaddon.compatibility.LangMessageKey
import com.github.mrjimin.betonquestaddon.compatibility.craftengine.objectives.CeObjective
import com.github.mrjimin.betonquestaddon.hook.CraftEngineHook.toIdOrNull
import net.momirealms.craftengine.bukkit.api.event.FurnitureInteractEvent
import org.betonquest.betonquest.api.logger.BetonQuestLogger
import org.betonquest.betonquest.instruction.Instruction
import org.betonquest.betonquest.instruction.variable.Variable
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener

class CeFurnitureInteract(
    instruction: Instruction,
    targetAmount: Variable<Number>,
    log: BetonQuestLogger,
    itemID: Variable<String>,
    val isCancel: Boolean
) : CeObjective(instruction, targetAmount, LangMessageKey.FURNITURE_INTERACT, log, itemID), Listener {

    @EventHandler
    fun FurnitureInteractEvent.onCeFurnitureInteract() {
        isCancelled = isCancel
        handle(furniture().id().toIdOrNull(), player)
    }

}