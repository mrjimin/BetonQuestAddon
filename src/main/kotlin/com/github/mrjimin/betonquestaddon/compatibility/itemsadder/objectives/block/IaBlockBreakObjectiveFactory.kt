package com.github.mrjimin.betonquestaddon.compatibility.itemsadder.objectives.block

import com.github.mrjimin.betonquestaddon.compatibility.itemsadder.IaParser
import com.github.mrjimin.betonquestaddon.util.getNumberNotLessThanZero
import org.betonquest.betonquest.api.Objective
import org.betonquest.betonquest.api.instruction.Instruction
import org.betonquest.betonquest.api.quest.objective.ObjectiveFactory

object IaBlockBreakObjectiveFactory : ObjectiveFactory {

    override fun parseInstruction(instruction: Instruction): Objective? {
        val itemID = instruction.get(IaParser)
        val targetAmount = instruction.getNumberNotLessThanZero("amount", 1)
        return IaBlockBreak(instruction, targetAmount, itemID)
    }
}