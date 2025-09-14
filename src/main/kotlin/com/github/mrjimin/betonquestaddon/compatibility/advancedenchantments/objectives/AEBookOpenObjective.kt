package com.github.mrjimin.betonquestaddon.compatibility.advancedenchantments.objectives

import com.github.mrjimin.betonquestaddon.compatibility.AbstractBaseObjective
import com.github.mrjimin.betonquestaddon.compatibility.LangMessageKey
import net.advancedplugins.ae.api.BookOpenEvent
import org.betonquest.betonquest.api.instruction.Instruction
import org.betonquest.betonquest.api.instruction.variable.Variable
import org.betonquest.betonquest.api.logger.BetonQuestLogger
import org.bukkit.event.EventHandler

class AEBookOpenObjective(
    instruction: Instruction,
    targetAmount: Variable<Number>,
    log: BetonQuestLogger,
    private val groups: Variable<List<String>>
) : AbstractBaseObjective(instruction, targetAmount, LangMessageKey.AE_BOOK_OPEN, log) {

    @EventHandler
    fun BookOpenEvent.onBookOpen() {
        val profile = getProfile(player)
        val expectedGroups = groups.getValue(profile)

        if ("ALL" in expectedGroups || group in expectedGroups) {
            handle(player)
        }
    }

}