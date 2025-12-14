package com.github.mrjimin.betonquestaddon.compatibilityold.itemsadder.objectives

import com.github.mrjimin.betonquestaddon.compatibilityold.ItemObjective
import com.github.mrjimin.betonquestaddon.compatibilityold.LangMessageKey
import dev.lone.itemsadder.api.CustomStack
import org.betonquest.betonquest.api.instruction.Instruction
import org.betonquest.betonquest.api.instruction.variable.Variable

open class IaObjective(
    instruction: Instruction,
    targetAmount: Variable<Number>,
    langMessageKey: LangMessageKey,
    itemID: Variable<CustomStack>
) : ItemObjective<CustomStack>(instruction, targetAmount, langMessageKey, itemID) {
    override fun matches(expected: CustomStack, inputId: String?): Boolean {
        return expected.namespacedID == inputId
    }
}