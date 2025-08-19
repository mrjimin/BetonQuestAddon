package com.github.mrjimin.betonquestaddon.compatibility.itemsadder.objectives

import com.github.mrjimin.betonquestaddon.compatibility.AbstractItemObjective
import com.github.mrjimin.betonquestaddon.compatibility.LangMessageKey
import dev.lone.itemsadder.api.CustomStack
import org.betonquest.betonquest.api.logger.BetonQuestLogger
import org.betonquest.betonquest.api.instruction.Instruction
import org.betonquest.betonquest.api.instruction.variable.Variable

open class IaObjective(
    instruction: Instruction,
    targetAmount: Variable<Number>,
    langMessageKey: LangMessageKey,
    log: BetonQuestLogger,
    itemID: Variable<CustomStack>
) : AbstractItemObjective<CustomStack>(instruction, targetAmount, langMessageKey, log, itemID) {
    override fun matches(expected: CustomStack, inputId: String?): Boolean {
        return expected.namespacedID == inputId
    }
}