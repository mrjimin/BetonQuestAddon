package com.github.mrjimin.betonquestaddon.compatibility.advancedenchantments.objectives

import com.github.mrjimin.betonquestaddon.util.getNumberNotLessThanOne
import org.betonquest.betonquest.api.Objective
import org.betonquest.betonquest.api.logger.BetonQuestLoggerFactory
import org.betonquest.betonquest.api.quest.objective.ObjectiveFactory
import org.betonquest.betonquest.instruction.Instruction
import org.betonquest.betonquest.instruction.variable.Variable

class AEBookOpenObjectiveFactory(
    private val loggerFactory: BetonQuestLoggerFactory
) : ObjectiveFactory {

    override fun parseInstruction(instruction: Instruction): Objective {
        val groups: Variable<List<String>> = instruction.getList { it }
        val targetAmount = instruction.getNumberNotLessThanOne("amount", 1)
        val log = loggerFactory.create(AEBookOpenObjective::class.java)
        return AEBookOpenObjective(instruction, targetAmount, log, groups)
    }
}