package com.github.seojimin0402.betonquestaddon.compatibility.nexo.objectives

import com.github.seojimin0402.betonquestaddon.util.event.ActionType
import com.github.seojimin0402.betonquestaddon.util.event.TargetType
import org.betonquest.betonquest.api.Objective
import org.betonquest.betonquest.api.instruction.Instruction
import org.betonquest.betonquest.api.instruction.argument.Argument
import org.betonquest.betonquest.api.quest.objective.ObjectiveFactory

class NexoObjectiveFactory(
    private val action: ActionType,
    private val target: TargetType
) : ObjectiveFactory {

    override fun parseInstruction(instruction: Instruction): Objective {
        val itemId = instruction[Argument.STRING]
        val amount = instruction.getValue("amount", Argument.NUMBER_NOT_LESS_THAN_ONE, 1)

        val message = when (action) {
            ActionType.BREAK -> "to_break"
            ActionType.PLACE -> "to_place"
        }

        return NexoObjective(
            instruction,
            amount,
            message,
            itemId,
            action,
            target
        )
    }
}
