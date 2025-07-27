package com.github.mrjimin.betonquestaddon.compatibility.craftengine.objectives.block

import com.github.mrjimin.betonquestaddon.compatibility.LangMessageKey
import com.github.mrjimin.betonquestaddon.compatibility.craftengine.objectives.CeObjective
import com.github.mrjimin.betonquestaddon.hook.CraftEngineHook.toIdOrNull
import net.momirealms.craftengine.bukkit.api.event.CustomBlockPlaceEvent
import org.betonquest.betonquest.api.logger.BetonQuestLogger
import org.betonquest.betonquest.instruction.Instruction
import org.betonquest.betonquest.instruction.variable.Variable
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener

class CeBlockPlace(
    instruction: Instruction,
    targetAmount: Variable<Number>,
    log: BetonQuestLogger,
    itemID: Variable<String>
) : CeObjective(instruction, targetAmount, LangMessageKey.BLOCK_PLACE, log, itemID), Listener {

    @EventHandler
    fun CustomBlockPlaceEvent.onCeBlockPlace() {
        handle(customBlock().id().toIdOrNull(), player)
    }
}