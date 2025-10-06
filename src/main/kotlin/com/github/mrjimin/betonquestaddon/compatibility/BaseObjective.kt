package com.github.mrjimin.betonquestaddon.compatibility

import org.betonquest.betonquest.BetonQuest
import org.betonquest.betonquest.api.CountingObjective
import org.betonquest.betonquest.api.instruction.Instruction
import org.betonquest.betonquest.api.instruction.variable.Variable
import org.betonquest.betonquest.api.profile.Profile
import org.bukkit.entity.Player
import org.bukkit.event.Listener

abstract class BaseObjective(
    instruction: Instruction,
    targetAmount: Variable<Number>,
    langMessageKey: LangMessageKey
) : CountingObjective(instruction, targetAmount, langMessageKey.key), Listener {

    protected fun getProfile(player: Player?): Profile? =
        player?.let { BetonQuest.getInstance().profileProvider.getProfile(it) }

    protected inline fun withProfile(player: Player?, block: (Profile) -> Unit) {
        val profile = getProfile(player) ?: return
        if (!containsPlayer(profile) || !checkConditions(profile)) return
        block(profile)
    }

    protected open fun checkMatch(profile: Profile, input: Any?): Boolean = true

    protected fun handle(player: Player?, input: Any? = null) = withProfile(player) { profile ->
        if (!checkMatch(profile, input)) return@withProfile
        getCountingData(profile).progress()
        completeIfDoneOrNotify(profile)
    }
}
