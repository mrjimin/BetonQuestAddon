package com.github.mrjimin.betonquestaddon.compatibility.nexo.objectives.block

import com.github.mrjimin.betonquestaddon.compatibility.nexo.NxParser
import com.github.mrjimin.betonquestaddon.compatibility.nexo.objectives.NxObjective
import com.github.mrjimin.betonquestaddon.util.getNumberNotLessThanZero
import org.betonquest.betonquest.api.Objective
import org.betonquest.betonquest.api.logger.BetonQuestLoggerFactory
import org.betonquest.betonquest.api.quest.objective.ObjectiveFactory
import org.betonquest.betonquest.api.instruction.Instruction

class NxBlockBreakObjectiveFactory(
    private val loggerFactory: BetonQuestLoggerFactory
) : ObjectiveFactory {

    override fun parseInstruction(instruction: Instruction): Objective {
        val itemID = instruction.get(NxParser)
        val targetAmount = instruction.getNumberNotLessThanZero("amount", 1)
        val log = loggerFactory.create(NxObjective::class.java)
        return NxBlockBreak(instruction, targetAmount, log, itemID)
    }
}