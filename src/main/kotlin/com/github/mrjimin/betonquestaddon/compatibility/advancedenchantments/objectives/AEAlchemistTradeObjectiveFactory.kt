package com.github.mrjimin.betonquestaddon.compatibility.advancedenchantments.objectives

import com.github.mrjimin.betonquestaddon.util.getNumberNotLessThanZero
import org.betonquest.betonquest.api.Objective
import org.betonquest.betonquest.api.instruction.Instruction
import org.betonquest.betonquest.api.logger.BetonQuestLoggerFactory
import org.betonquest.betonquest.api.quest.objective.ObjectiveFactory

object AEAlchemistTradeObjectiveFactory : ObjectiveFactory {

    override fun parseInstruction(instruction: Instruction): Objective {
        val targetAmount = instruction.getNumberNotLessThanZero("amount", 1)
        return AEAlchemistTradeObjective(instruction, targetAmount)
    }
}