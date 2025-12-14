package com.github.mrjimin.betonquestaddon.compatibilityold.customcrops.objectives

import com.github.mrjimin.betonquestaddon.hook.CustomCropsHook
import org.betonquest.betonquest.api.Objective
import org.betonquest.betonquest.api.instruction.Instruction
import org.betonquest.betonquest.api.quest.objective.ObjectiveFactory

object CCropsPlantObjectiveFactory : ObjectiveFactory {

    override fun parseInstruction(instruction: Instruction): Objective {
        return CustomCropsHook.create(instruction, ::CCropsPlantObjective)
    }
}