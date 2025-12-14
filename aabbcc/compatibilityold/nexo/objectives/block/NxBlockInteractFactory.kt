package com.github.mrjimin.betonquestaddon.compatibilityold.nexo.objectives.block

import com.github.mrjimin.betonquestaddon.compatibilityold.nexo.NxParser
import com.github.mrjimin.betonquestaddon.util.getNumberNotLessThanZero
import org.betonquest.betonquest.api.Objective
import org.betonquest.betonquest.api.instruction.Instruction
import org.betonquest.betonquest.api.quest.objective.ObjectiveFactory

object NxBlockInteractFactory: ObjectiveFactory {

    override fun parseInstruction(instruction: Instruction): Objective {
        val itemID = instruction.get(NxParser)
        val targetAmount = instruction.getNumberNotLessThanZero("amount", 1)
        val isCancel = instruction.hasArgument("cancel")
        return NxBlockInteract(instruction, targetAmount, itemID, isCancel)
    }
}