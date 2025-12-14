package com.github.mrjimin.betonquestaddon.compatibilityold.craftengine.objectives.block

import com.github.mrjimin.betonquestaddon.compatibilityold.LangMessageKey
import com.github.mrjimin.betonquestaddon.compatibilityold.craftengine.objectives.CeObjective
import com.github.mrjimin.betonquestaddon.hook.CraftEngineHook.toIdOrNull
import net.momirealms.craftengine.bukkit.api.event.CustomBlockPlaceEvent
import org.betonquest.betonquest.api.instruction.Instruction
import org.betonquest.betonquest.api.instruction.variable.Variable
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener

class CeBlockPlace(
    instruction: Instruction,
    targetAmount: Variable<Number>,
    itemID: Variable<String>
) : CeObjective(instruction, targetAmount, LangMessageKey.BLOCK_PLACE, itemID), Listener {

    @EventHandler
    fun CustomBlockPlaceEvent.onCeBlockPlace() {
        handle(player, customBlock().id().toIdOrNull())
    }
}