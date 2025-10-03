package com.github.mrjimin.betonquestaddon.compatibility.advancedenchantments.objectives

import com.github.mrjimin.betonquestaddon.util.getNumberNotLessThanZero
import org.betonquest.betonquest.api.Objective
import org.betonquest.betonquest.api.instruction.Instruction
import org.betonquest.betonquest.api.quest.objective.ObjectiveFactory

object AEBookOpenObjectiveFactory : ObjectiveFactory {

    override fun parseInstruction(instruction: Instruction): Objective {
        val groups = instruction.getList { it }
        val targetAmount = instruction.getNumberNotLessThanZero("amount", 1)
        return AEBookOpenObjective(instruction, targetAmount, groups)
    }
}