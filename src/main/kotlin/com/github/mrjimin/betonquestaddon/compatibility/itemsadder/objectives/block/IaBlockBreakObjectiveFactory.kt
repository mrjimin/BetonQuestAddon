package com.github.mrjimin.betonquestaddon.compatibility.itemsadder.objectives.block

import com.github.mrjimin.betonquestaddon.compatibility.itemsadder.IaParser
import com.github.mrjimin.betonquestaddon.compatibility.itemsadder.objectives.IaObjective
import com.github.mrjimin.betonquestaddon.util.getNumberNotLessThanZero
import org.betonquest.betonquest.api.Objective
import org.betonquest.betonquest.api.logger.BetonQuestLoggerFactory
import org.betonquest.betonquest.api.quest.objective.ObjectiveFactory
import org.betonquest.betonquest.api.instruction.Instruction

class IaBlockBreakObjectiveFactory(
    private val loggerFactory: BetonQuestLoggerFactory
) : ObjectiveFactory {

    override fun parseInstruction(instruction: Instruction): Objective? {
        val itemID = instruction.get(IaParser)
        val targetAmount = instruction.getNumberNotLessThanZero("amount", 1)
        val log = loggerFactory.create(IaObjective::class.java)
        return IaBlockBreak(instruction, targetAmount, log, itemID)
    }
}