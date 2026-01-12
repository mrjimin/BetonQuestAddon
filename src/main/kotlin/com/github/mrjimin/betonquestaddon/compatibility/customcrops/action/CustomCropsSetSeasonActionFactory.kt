package com.github.mrjimin.betonquestaddon.compatibility.customcrops.action

import net.momirealms.customcrops.api.core.world.Season
import org.betonquest.betonquest.api.instruction.Instruction
import org.betonquest.betonquest.api.quest.action.PlayerAction
import org.betonquest.betonquest.api.quest.action.PlayerActionFactory

class CustomCropsSetSeasonActionFactory : PlayerActionFactory {
    override fun parsePlayer(instruction: Instruction): PlayerAction {
        val world = instruction.world().get()
        val season = instruction.enumeration(Season::class.java).get()
        return CustomCropsSetSeasonAction(world, season)
    }
}