package com.github.mrjimin.betonquestaddon.compatibility.craftengine.objectives.furniture

import com.github.mrjimin.betonquestaddon.compatibility.craftengine.CeParser
import com.github.mrjimin.betonquestaddon.compatibility.craftengine.objectives.CeObjective
import com.github.mrjimin.betonquestaddon.util.getNumberNotLessThanOne
import org.betonquest.betonquest.api.Objective
import org.betonquest.betonquest.api.logger.BetonQuestLoggerFactory
import org.betonquest.betonquest.api.quest.objective.ObjectiveFactory
import org.betonquest.betonquest.instruction.Instruction

class CeFurnitureInteractObjectiveFactory(
    private val loggerFactory: BetonQuestLoggerFactory
) : ObjectiveFactory {

    override fun parseInstruction(instruction: Instruction): Objective {
        val itemID = instruction.get(CeParser)
        val targetAmount = instruction.getNumberNotLessThanOne("amount", 1)
        val isCancel = instruction.hasArgument("cancel")
        val log = loggerFactory.create(CeObjective::class.java)
        return CeFurnitureInteract(instruction, targetAmount, log, itemID, isCancel)
    }
}