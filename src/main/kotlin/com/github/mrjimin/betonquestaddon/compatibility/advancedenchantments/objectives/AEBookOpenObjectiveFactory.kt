package com.github.mrjimin.betonquestaddon.compatibility.advancedenchantments.objectives

import com.github.mrjimin.betonquestaddon.util.getNumberNotLessThanZero
import org.betonquest.betonquest.api.Objective
import org.betonquest.betonquest.api.instruction.Instruction
import org.betonquest.betonquest.api.logger.BetonQuestLoggerFactory
import org.betonquest.betonquest.api.quest.objective.ObjectiveFactory

class AEBookOpenObjectiveFactory(
    private val loggerFactory: BetonQuestLoggerFactory
) : ObjectiveFactory {

    override fun parseInstruction(instruction: Instruction): Objective {
        val groups = instruction.getList { it }
        val targetAmount = instruction.getNumberNotLessThanZero("amount", 1)
        val log = loggerFactory.create(AEBookOpenObjective::class.java)
        return AEBookOpenObjective(instruction, targetAmount, log, groups)
    }
}