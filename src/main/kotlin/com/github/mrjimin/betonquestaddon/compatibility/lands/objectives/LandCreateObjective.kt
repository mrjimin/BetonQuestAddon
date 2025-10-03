package com.github.mrjimin.betonquestaddon.compatibility.lands.objectives

import com.github.mrjimin.betonquestaddon.compatibility.AbstractBaseObjective
import com.github.mrjimin.betonquestaddon.compatibility.LangMessageKey
import me.angeschossen.lands.api.events.LandCreateEvent
import org.betonquest.betonquest.api.logger.BetonQuestLogger
import org.betonquest.betonquest.api.instruction.Instruction
import org.betonquest.betonquest.api.instruction.variable.Variable
import org.bukkit.event.EventHandler

class LandCreateObjective(
    instruction: Instruction
) : AbstractBaseObjective(instruction, Variable(1), LangMessageKey.LANDS_CREATE) {

    @EventHandler
    fun LandCreateEvent.onLandCreate() {
        handle(landPlayer?.player)
    }
}