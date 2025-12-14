package com.github.mrjimin.betonquestaddon.compatibilityold.craftengine.objectives.block

import com.github.mrjimin.betonquestaddon.compatibilityold.craftengine.CeParser
import com.github.mrjimin.betonquestaddon.util.getNumberNotLessThanZero
import org.betonquest.betonquest.api.Objective
import org.betonquest.betonquest.api.instruction.Instruction
import org.betonquest.betonquest.api.quest.objective.ObjectiveFactory

object CeBlockBreakObjectiveFactory : ObjectiveFactory {

    override fun parseInstruction(instruction: Instruction): Objective {
        val itemID = instruction.get(CeParser)
        val targetAmount = instruction.getNumberNotLessThanZero("amount", 1)
        return CeBlockBreak(instruction, targetAmount, itemID)
    }
}