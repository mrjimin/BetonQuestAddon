package com.github.mrjimin.betonquestaddon.compatibility.craftengine.objectives.furniture

import com.github.mrjimin.betonquestaddon.compatibility.LangMessageKey
import com.github.mrjimin.betonquestaddon.compatibility.craftengine.objectives.CeObjective
import com.github.mrjimin.betonquestaddon.hook.CraftEngineHook.toIdOrNull
import net.momirealms.craftengine.bukkit.api.event.FurnitureBreakEvent
import org.betonquest.betonquest.api.logger.BetonQuestLogger
import org.betonquest.betonquest.instruction.Instruction
import org.betonquest.betonquest.instruction.variable.Variable
import org.bukkit.event.EventHandler
import org.bukkit.event.EventPriority
import org.bukkit.event.Listener

class CeFurnitureBreak(
    instruction: Instruction,
    targetAmount: Variable<Number>,
    log: BetonQuestLogger,
    itemID: Variable<String>
) : CeObjective(instruction, targetAmount, LangMessageKey.FURNITURE_BREAK, log, itemID), Listener {

    @EventHandler(ignoreCancelled = true, priority = EventPriority.MONITOR)
    fun FurnitureBreakEvent.onCeFurniturePlace() {
        handle(furniture().id().toIdOrNull(), player)
    }
}