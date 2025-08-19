package com.github.mrjimin.betonquestaddon.compatibility.lands.objectives

import com.github.mrjimin.betonquestaddon.compatibility.AbstractSimpleObjective
import com.github.mrjimin.betonquestaddon.compatibility.LangMessageKey
import me.angeschossen.lands.api.events.LandDeleteEvent
import org.betonquest.betonquest.api.logger.BetonQuestLogger
import org.betonquest.betonquest.api.instruction.Instruction
import org.betonquest.betonquest.api.instruction.variable.Variable
import org.bukkit.event.EventHandler

class LandDeleteObjective(
    instruction: Instruction,
    targetAmount: Variable<Number>,
    log: BetonQuestLogger
) : AbstractSimpleObjective(instruction, targetAmount, LangMessageKey.LANDS_DELETE, log) {

    @EventHandler
    fun LandDeleteEvent.onDelete() {
        handle(landPlayer?.player)
    }
}