package com.github.mrjimin.betonquestaddon.compatibility.nexo.objectives.furniture

import com.github.mrjimin.betonquestaddon.compatibility.nexo.NxParser
import com.github.mrjimin.betonquestaddon.util.getNumberNotLessThanZero
import org.betonquest.betonquest.api.Objective
import org.betonquest.betonquest.api.instruction.Instruction
import org.betonquest.betonquest.api.quest.objective.ObjectiveFactory

object NxFurnitureInteractObjectiveFactory : ObjectiveFactory {

    override fun parseInstruction(instruction: Instruction): Objective {
        val itemID = instruction.get(NxParser)
        val targetAmount = instruction.getNumberNotLessThanZero("amount", 1)
        val isCancel = instruction.hasArgument("cancel")
        return NxFurnitureInteract(instruction, targetAmount, itemID, isCancel)
    }
}