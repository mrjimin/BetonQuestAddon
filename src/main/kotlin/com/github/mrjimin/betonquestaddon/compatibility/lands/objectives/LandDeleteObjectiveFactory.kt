package com.github.mrjimin.betonquestaddon.compatibility.lands.objectives

import org.betonquest.betonquest.api.Objective
import org.betonquest.betonquest.api.quest.objective.ObjectiveFactory
import org.betonquest.betonquest.api.instruction.Instruction
import org.betonquest.betonquest.api.instruction.variable.Variable

object LandDeleteObjectiveFactory : ObjectiveFactory {

    override fun parseInstruction(instruction: Instruction): Objective {
        return LandDeleteObjective(instruction)
    }
}