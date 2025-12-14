package com.github.mrjimin.betonquestaddon.compatibilityold.lands.objectives

import com.github.mrjimin.betonquestaddon.compatibilityold.BaseObjective
import com.github.mrjimin.betonquestaddon.compatibilityold.LangMessageKey
import me.angeschossen.lands.api.events.LandCreateEvent
import org.betonquest.betonquest.api.instruction.Instruction
import org.betonquest.betonquest.api.instruction.variable.Variable
import org.bukkit.event.EventHandler

class LandCreateObjective(
    instruction: Instruction
) : BaseObjective(instruction, Variable(1), LangMessageKey.LANDS_CREATE) {

    @EventHandler
    fun LandCreateEvent.onLandCreate() {
        handle(landPlayer?.player)
    }
}