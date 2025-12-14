package com.github.mrjimin.betonquestaddon.compatibilityold.craftengine.objectives.furniture

import com.github.mrjimin.betonquestaddon.compatibilityold.LangMessageKey
import com.github.mrjimin.betonquestaddon.compatibilityold.craftengine.objectives.CeObjective
import com.github.mrjimin.betonquestaddon.hook.CraftEngineHook.toIdOrNull
import net.momirealms.craftengine.bukkit.api.event.FurniturePlaceEvent
import org.betonquest.betonquest.api.instruction.Instruction
import org.betonquest.betonquest.api.instruction.variable.Variable
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener

class CeFurniturePlace(
    instruction: Instruction,
    targetAmount: Variable<Number>,
    itemID: Variable<String>
) : CeObjective(instruction, targetAmount, LangMessageKey.FURNITURE_PLACE, itemID), Listener {

    @EventHandler
    fun FurniturePlaceEvent.onCeFurniturePlace() {
        handle(player, furniture().id().toIdOrNull())
    }
}