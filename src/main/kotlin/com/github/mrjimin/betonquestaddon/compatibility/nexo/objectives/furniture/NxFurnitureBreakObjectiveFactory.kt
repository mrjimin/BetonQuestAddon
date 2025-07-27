package com.github.mrjimin.betonquestaddon.compatibility.nexo.objectives.furniture

import com.github.mrjimin.betonquestaddon.compatibility.craftengine.objectives.CeObjective
import com.github.mrjimin.betonquestaddon.compatibility.nexo.NxParser
import com.github.mrjimin.betonquestaddon.util.getNumberNotLessThanOne
import org.betonquest.betonquest.api.Objective
import org.betonquest.betonquest.api.logger.BetonQuestLoggerFactory
import org.betonquest.betonquest.api.quest.objective.ObjectiveFactory
import org.betonquest.betonquest.instruction.Instruction

class NxFurnitureBreakObjectiveFactory(
    private val loggerFactory: BetonQuestLoggerFactory
) : ObjectiveFactory {

    override fun parseInstruction(instruction: Instruction): Objective {
        val itemID = instruction.get(NxParser)
        val targetAmount = instruction.getNumberNotLessThanOne("amount", 1)
        val log = loggerFactory.create(CeObjective::class.java)
        return NxFurnitureBreak(instruction, targetAmount, log, itemID)
    }
}