package com.github.mrjimin.betonquestaddon.compatibility.craftengine.objectives.block

import com.github.mrjimin.betonquestaddon.compatibility.LangMessageKey
import com.github.mrjimin.betonquestaddon.compatibility.craftengine.objectives.CeObjective
import com.github.mrjimin.betonquestaddon.hook.CraftEngineHook.toIdOrNull
import net.momirealms.craftengine.bukkit.api.event.CustomBlockBreakEvent
import org.betonquest.betonquest.api.instruction.Instruction
import org.betonquest.betonquest.api.instruction.variable.Variable
import org.betonquest.betonquest.api.logger.BetonQuestLogger
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener

class CeBlockBreak(
    instruction: Instruction,
    targetAmount: Variable<Number>,
    itemID: Variable<String>
) : CeObjective(instruction, targetAmount, LangMessageKey.BLOCK_BREAK, itemID), Listener {

    @EventHandler
    fun CustomBlockBreakEvent.onCeBlockPlace() {
        handle(customBlock().id().toIdOrNull(), player)
    }
}