package com.github.mrjimin.betonquestaddon.compatibility.craftengine.objectives.furniture

import com.github.mrjimin.betonquestaddon.compatibility.craftengine.CeParser
import com.github.mrjimin.betonquestaddon.compatibility.craftengine.objectives.CeObjective
import com.github.mrjimin.betonquestaddon.util.getNumberNotLessThanZero
import org.betonquest.betonquest.api.Objective
import org.betonquest.betonquest.api.quest.objective.ObjectiveFactory
import org.betonquest.betonquest.api.instruction.Instruction

object CeFurnitureInteractObjectiveFactory : ObjectiveFactory {

    override fun parseInstruction(instruction: Instruction): Objective {
        val itemID = instruction.get(CeParser)
        val targetAmount = instruction.getNumberNotLessThanZero("amount", 1)
        val isCancel = instruction.hasArgument("cancel")
        return CeFurnitureInteract(instruction, targetAmount, itemID, isCancel)
    }
}