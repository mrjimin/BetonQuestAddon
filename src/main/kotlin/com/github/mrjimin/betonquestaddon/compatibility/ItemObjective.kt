package com.github.mrjimin.betonquestaddon.compatibility

import com.github.mrjimin.betonquestaddon.betonquest.BetonQuestAddon
import org.betonquest.betonquest.api.instruction.Instruction
import org.betonquest.betonquest.api.instruction.variable.Variable
import org.betonquest.betonquest.api.profile.Profile
import org.betonquest.betonquest.api.quest.QuestException

abstract class ItemObjective<T>(
    instruction: Instruction,
    targetAmount: Variable<Number>,
    langMessageKey: LangMessageKey,
    private val itemID: Variable<T>
) : BaseObjective(instruction, targetAmount, langMessageKey) {

    protected abstract fun matches(expected: T, inputId: String?): Boolean

    override fun checkMatch(profile: Profile, input: Any?): Boolean {
        val inputId = input as? String ?: return false
        return try {
            val expected = itemID.getValue(profile)
            matches(expected, inputId)
        } catch (e: QuestException) {
            BetonQuestAddon.logger.warn("Could not resolve Item Variable in objective '${instruction.id}': ${e.message}", e)
            false
        }
    }
}
