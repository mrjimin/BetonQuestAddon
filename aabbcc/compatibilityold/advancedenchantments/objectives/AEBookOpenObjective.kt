package com.github.mrjimin.betonquestaddon.compatibilityold.advancedenchantments.objectives

import com.github.mrjimin.betonquestaddon.compatibilityold.BaseObjective
import com.github.mrjimin.betonquestaddon.compatibilityold.LangMessageKey
import net.advancedplugins.ae.api.BookOpenEvent
import org.betonquest.betonquest.api.instruction.Instruction
import org.betonquest.betonquest.api.instruction.variable.Variable
import org.bukkit.event.EventHandler

class AEBookOpenObjective(
    instruction: Instruction,
    targetAmount: Variable<Number>,
    private val groups: Variable<List<String>>
) : BaseObjective(instruction, targetAmount, LangMessageKey.AE_BOOK_OPEN) {

    @EventHandler
    fun BookOpenEvent.onBookOpen() {
        val profile = getProfile(player)
        val expectedGroups = groups.getValue(profile)

        if ("ALL" in expectedGroups || group in expectedGroups) {
            handle(player)
        }
    }

}