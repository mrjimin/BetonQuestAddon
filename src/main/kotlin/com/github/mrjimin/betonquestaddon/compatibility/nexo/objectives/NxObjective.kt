package com.github.mrjimin.betonquestaddon.compatibility.nexo.objectives

import com.github.mrjimin.betonquestaddon.compatibility.ItemObjective
import com.github.mrjimin.betonquestaddon.compatibility.LangMessageKey
import org.betonquest.betonquest.api.instruction.Instruction
import org.betonquest.betonquest.api.instruction.variable.Variable

open class NxObjective(
    instruction: Instruction,
    targetAmount: Variable<Number>,
    langMessageKey: LangMessageKey,
    itemID: Variable<String>
) : ItemObjective<String>(instruction, targetAmount, langMessageKey, itemID) {
    override fun matches(expected: String, inputId: String?): Boolean {
        return expected == inputId
    }
}