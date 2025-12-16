package com.github.mrjimin.betonquestaddon.compatibility.nexo.objectives

import com.github.mrjimin.betonquestaddon.util.event.ActionType
import com.github.mrjimin.betonquestaddon.util.event.TargetType
import org.betonquest.betonquest.api.Objective
import org.betonquest.betonquest.api.instruction.Instruction
import org.betonquest.betonquest.api.instruction.argument.Argument
import org.betonquest.betonquest.api.quest.objective.ObjectiveFactory

class NexoObjectiveFactory(
    private val targetType: TargetType,
    private val actionType: ActionType
) : ObjectiveFactory {

    override fun parseInstruction(instruction: Instruction): Objective {
        val itemId = instruction[Argument.STRING]
        val amount = instruction.getValue("amount", Argument.NUMBER_NOT_LESS_THAN_ONE, 1)

        val message = "${targetType.name.lowercase()}_to_${actionType.name.lowercase()}"

        return when (targetType) {
            TargetType.FURNITURE -> NexoFurnitureObjective(instruction, amount, message, itemId, actionType)
            TargetType.BLOCK -> NexoBlockObjective(instruction, amount, message, itemId, actionType)
        }
    }
}
