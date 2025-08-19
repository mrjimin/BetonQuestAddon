package com.github.mrjimin.betonquestaddon.compatibility.craftengine.objectives

import com.github.mrjimin.betonquestaddon.compatibility.AbstractItemObjective
import com.github.mrjimin.betonquestaddon.compatibility.LangMessageKey
import org.betonquest.betonquest.api.logger.BetonQuestLogger
import org.betonquest.betonquest.api.instruction.Instruction
import org.betonquest.betonquest.api.instruction.variable.Variable

open class CeObjective(
    instruction: Instruction,
    targetAmount: Variable<Number>,
    langMessageKey: LangMessageKey,
    log: BetonQuestLogger,
    itemID: Variable<String>
) : AbstractItemObjective<String>(instruction, targetAmount, langMessageKey, log, itemID) {
    override fun matches(expected: String, inputId: String?): Boolean {
        return expected == inputId
    }
}