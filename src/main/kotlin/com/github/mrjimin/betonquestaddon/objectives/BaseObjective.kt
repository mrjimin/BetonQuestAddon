package com.github.mrjimin.betonquestaddon.objectives

import org.betonquest.betonquest.BetonQuest
import org.betonquest.betonquest.api.CountingObjective
import org.betonquest.betonquest.api.instruction.Instruction
import org.betonquest.betonquest.api.instruction.Argument
import org.betonquest.betonquest.api.profile.Profile
import org.bukkit.entity.Player
import org.bukkit.event.Listener

abstract class BaseObjective(
    instruction: Instruction,
    targetAmount: Argument<Number>?,
    message: String
) : CountingObjective(instruction, targetAmount, message), Listener {

    protected open fun checkMatch(profile: Profile, input: Any?): Boolean = true

    protected fun handle(player: Player?, input: Any? = null) {
        val profile = player?.let(BetonQuest.getInstance().profileProvider::getProfile) ?: return
        if (!containsPlayer(profile) || !checkConditions(profile) || !checkMatch(profile, input)) return

        getCountingData(profile).apply {
            progress()
            completeIfDoneOrNotify(profile)
        }
    }
}
