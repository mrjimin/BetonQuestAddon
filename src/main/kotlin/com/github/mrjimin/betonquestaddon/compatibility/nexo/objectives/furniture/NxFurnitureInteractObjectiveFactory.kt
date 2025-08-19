package com.github.mrjimin.betonquestaddon.compatibility.nexo.objectives.furniture

import com.github.mrjimin.betonquestaddon.compatibility.craftengine.objectives.CeObjective
import com.github.mrjimin.betonquestaddon.compatibility.nexo.NxParser
import com.github.mrjimin.betonquestaddon.util.getNumberNotLessThanZero
import org.betonquest.betonquest.api.Objective
import org.betonquest.betonquest.api.logger.BetonQuestLoggerFactory
import org.betonquest.betonquest.api.quest.objective.ObjectiveFactory
import org.betonquest.betonquest.api.instruction.Instruction

class NxFurnitureInteractObjectiveFactory(
    private val loggerFactory: BetonQuestLoggerFactory
) : ObjectiveFactory {

    override fun parseInstruction(instruction: Instruction): Objective {
        val itemID = instruction.get(NxParser)
        val targetAmount = instruction.getNumberNotLessThanZero("amount", 1)
        val isCancel = instruction.hasArgument("cancel")
        val log = loggerFactory.create(CeObjective::class.java)
        return NxFurnitureInteract(instruction, targetAmount, log, itemID, isCancel)
    }
}