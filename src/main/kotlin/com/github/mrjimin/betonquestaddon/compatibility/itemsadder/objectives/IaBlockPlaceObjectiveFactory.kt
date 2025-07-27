package com.github.mrjimin.betonquestaddon.compatibility.itemsadder.objectives

import com.github.mrjimin.betonquestaddon.compatibility.itemsadder.IaParser
import com.github.mrjimin.betonquestaddon.util.getNumberNotLessThanOne
import org.betonquest.betonquest.api.Objective
import org.betonquest.betonquest.api.logger.BetonQuestLoggerFactory
import org.betonquest.betonquest.api.quest.objective.ObjectiveFactory
import org.betonquest.betonquest.instruction.Instruction

class IaBlockPlaceObjectiveFactory(
    private val loggerFactory: BetonQuestLoggerFactory
) : ObjectiveFactory {

    override fun parseInstruction(instruction: Instruction): Objective? {
        val itemID = instruction.get(IaParser)
        val targetAmount = instruction.getNumberNotLessThanOne("amount", 1)
        val log = loggerFactory.create(IaBlockObjective::class.java)
        return IaBlockPlace(instruction, targetAmount, log, itemID)
    }
}