package com.github.mrjimin.betonquestaddon.compatibilityold.craftengine.objectives.block

import com.github.mrjimin.betonquestaddon.compatibilityold.craftengine.CeParser
import com.github.mrjimin.betonquestaddon.util.getNumberNotLessThanZero
import org.betonquest.betonquest.api.Objective
import org.betonquest.betonquest.api.quest.objective.ObjectiveFactory
import org.betonquest.betonquest.api.instruction.Instruction

object CeBlockInteractObjectiveFactory : ObjectiveFactory {

    override fun parseInstruction(instruction: Instruction): Objective {
        val itemID = instruction.get(CeParser)
        val targetAmount = instruction.getNumberNotLessThanZero("amount", 1)
        val isCancel = instruction.hasArgument("cancel")
        return CeBlockInteract(instruction, targetAmount, itemID, isCancel)
    }
}