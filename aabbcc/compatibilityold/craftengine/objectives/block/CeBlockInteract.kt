package com.github.mrjimin.betonquestaddon.compatibilityold.craftengine.objectives.block

import com.github.mrjimin.betonquestaddon.compatibilityold.LangMessageKey
import com.github.mrjimin.betonquestaddon.compatibilityold.craftengine.objectives.CeObjective
import com.github.mrjimin.betonquestaddon.hook.CraftEngineHook.toIdOrNull
import net.momirealms.craftengine.bukkit.api.event.CustomBlockInteractEvent
import org.betonquest.betonquest.api.instruction.Instruction
import org.betonquest.betonquest.api.instruction.variable.Variable
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener

class CeBlockInteract(
    instruction: Instruction,
    targetAmount: Variable<Number>,
    itemID: Variable<String>,
    val isCancel: Boolean
) : CeObjective(instruction, targetAmount, LangMessageKey.BLOCK_INTERACT, itemID), Listener {

    @EventHandler
    fun CustomBlockInteractEvent.onCeBlockInteract() {
        isCancelled = isCancel
        handle(player, customBlock().id().toIdOrNull())
    }
}