package com.github.mrjimin.betonquestaddon.compatibility

import org.betonquest.betonquest.BetonQuest
import org.betonquest.betonquest.api.CountingObjective
import org.betonquest.betonquest.api.instruction.Instruction
import org.betonquest.betonquest.api.instruction.variable.Variable
import org.betonquest.betonquest.api.logger.BetonQuestLogger
import org.betonquest.betonquest.api.profile.OnlineProfile
import org.bukkit.entity.Player
import org.bukkit.event.Listener

abstract class AbstractBaseObjective(
    protected val instruction: Instruction,
    targetAmount: Variable<Number>,
    langMessageKey: LangMessageKey,
    protected val log: BetonQuestLogger
) : CountingObjective(instruction, targetAmount, langMessageKey.key), Listener {

    protected fun getProfile(player: Player?): OnlineProfile =
        BetonQuest.getInstance().profileProvider.getProfile(player)

    protected fun handle(player: Player?, input: Any? = null) {
        val profile = getProfile(player)
        if (!containsPlayer(profile) || !checkConditions(profile)) return

        if (checkMatch(profile, input)) {
            getCountingData(profile).progress()
            completeIfDoneOrNotify(profile)
        }
    }

    protected open fun checkMatch(profile: OnlineProfile, input: Any?): Boolean = true
}
