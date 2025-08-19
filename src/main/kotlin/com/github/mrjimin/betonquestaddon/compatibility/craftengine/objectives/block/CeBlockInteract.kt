package com.github.mrjimin.betonquestaddon.compatibility.craftengine.objectives.block

import com.github.mrjimin.betonquestaddon.compatibility.LangMessageKey
import com.github.mrjimin.betonquestaddon.compatibility.craftengine.objectives.CeObjective
import com.github.mrjimin.betonquestaddon.hook.CraftEngineHook.toIdOrNull
import net.momirealms.craftengine.bukkit.api.event.CustomBlockInteractEvent
import org.betonquest.betonquest.api.logger.BetonQuestLogger
import org.betonquest.betonquest.api.instruction.Instruction
import org.betonquest.betonquest.api.instruction.variable.Variable
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener

class CeBlockInteract(
    instruction: Instruction,
    targetAmount: Variable<Number>,
    log: BetonQuestLogger,
    itemID: Variable<String>,
    val isCancel: Boolean
) : CeObjective(instruction, targetAmount, LangMessageKey.BLOCK_INTERACT, log, itemID), Listener {

    @EventHandler
    fun CustomBlockInteractEvent.onCeBlockInteract() {
        isCancelled = isCancel
        handle(customBlock().id().toIdOrNull(), player)
    }
}