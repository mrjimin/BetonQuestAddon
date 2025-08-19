package com.github.mrjimin.betonquestaddon.compatibility

import org.betonquest.betonquest.api.logger.BetonQuestLogger
import org.betonquest.betonquest.api.instruction.Instruction
import org.betonquest.betonquest.api.instruction.variable.Variable
import org.bukkit.entity.Player

abstract class AbstractSimpleObjective(
    instruction: Instruction,
    targetAmount: Variable<Number>,
    langMessageKey: LangMessageKey,
    log: BetonQuestLogger
) : AbstractBaseObjective(instruction, targetAmount, langMessageKey, log) {

    protected fun handle(player: Player?) {
        val profile = getProfile(player)
        if (!containsPlayer(profile) || !checkConditions(profile)) return

        getCountingData(profile).progress()
        completeIfDoneOrNotify(profile)
    }
}
