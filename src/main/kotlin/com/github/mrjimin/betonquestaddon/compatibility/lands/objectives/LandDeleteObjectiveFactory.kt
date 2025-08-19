package com.github.mrjimin.betonquestaddon.compatibility.lands.objectives

import org.betonquest.betonquest.api.Objective
import org.betonquest.betonquest.api.logger.BetonQuestLoggerFactory
import org.betonquest.betonquest.api.quest.objective.ObjectiveFactory
import org.betonquest.betonquest.api.instruction.Instruction
import org.betonquest.betonquest.api.instruction.variable.Variable

class LandDeleteObjectiveFactory(
    private val loggerFactory: BetonQuestLoggerFactory
) : ObjectiveFactory {

    override fun parseInstruction(instruction: Instruction): Objective {
        val log = loggerFactory.create(LandDeleteObjective::class.java)
        return LandDeleteObjective(instruction, Variable(1), log)
    }
}