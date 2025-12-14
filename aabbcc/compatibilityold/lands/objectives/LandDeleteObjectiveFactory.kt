package com.github.mrjimin.betonquestaddon.compatibilityold.lands.objectives

import org.betonquest.betonquest.api.Objective
import org.betonquest.betonquest.api.quest.objective.ObjectiveFactory
import org.betonquest.betonquest.api.instruction.Instruction

object LandDeleteObjectiveFactory : ObjectiveFactory {

    override fun parseInstruction(instruction: Instruction): Objective {
        return LandDeleteObjective(instruction)
    }
}