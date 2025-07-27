package com.github.mrjimin.betonquestaddon.compatibility

import org.betonquest.betonquest.api.logger.BetonQuestLogger
import org.betonquest.betonquest.api.profile.OnlineProfile
import org.betonquest.betonquest.api.quest.QuestException
import org.betonquest.betonquest.instruction.Instruction
import org.betonquest.betonquest.instruction.variable.Variable
import org.bukkit.entity.Player

abstract class AbstractItemObjective<T>(
    instruction: Instruction,
    targetAmount: Variable<Number>,
    langMessageKey: LangMessageKey,
    log: BetonQuestLogger,
    private val itemID: Variable<T>
) : AbstractBaseObjective(instruction, targetAmount, langMessageKey, log) {

    protected abstract fun matches(expected: T, inputId: String?): Boolean

    protected fun handle(namespacedID: String?, player: Player?) {
        val profile: OnlineProfile = getProfile(player)
        try {
            val expected = itemID.getValue(profile)
            if (containsPlayer(profile) && matches(expected, namespacedID) && checkConditions(profile)) {
                getCountingData(profile).progress()
                completeIfDoneOrNotify(profile)
            }
        } catch (e: QuestException) {
            log.warn("Could not resolve Item Variable in objective '${instruction.id}': ${e.message}", e)
        }
    }
}
