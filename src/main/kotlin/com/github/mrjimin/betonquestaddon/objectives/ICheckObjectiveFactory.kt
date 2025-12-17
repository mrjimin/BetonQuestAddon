package com.github.mrjimin.betonquestaddon.objectives

import com.github.mrjimin.betonquestaddon.util.event.ActionType
import com.github.mrjimin.betonquestaddon.util.event.TargetType
import org.betonquest.betonquest.api.Objective
import org.betonquest.betonquest.api.instruction.Instruction
import org.betonquest.betonquest.api.instruction.argument.Argument
import org.betonquest.betonquest.api.instruction.variable.Variable
import org.betonquest.betonquest.api.quest.objective.ObjectiveFactory

interface ICheckObjectiveFactory : ObjectiveFactory {

    val targetType: TargetType
    val actionType: ActionType

    override fun parseInstruction(instruction: Instruction): Objective {
        val itemId = instruction[Argument.STRING]
        val amount = instruction.getValue(
            "amount",
            Argument.NUMBER_NOT_LESS_THAN_ONE,
            1
        )
        val isCancelled = instruction.getValue(
            "isCancelled",
            Argument.BOOLEAN
        )

        val message =
            "${targetType.name.lowercase()}_to_${actionType.name.lowercase()}"

        return when (targetType) {
            TargetType.FURNITURE ->
                createFurniture(
                    instruction,
                    amount,
                    message,
                    itemId,
                    actionType,
                    isCancelled
                )

            TargetType.BLOCK ->
                createBlock(
                    instruction,
                    amount,
                    message,
                    itemId,
                    actionType,
                    isCancelled
                )
        }
    }

    fun createFurniture(
        instruction: Instruction,
        amount: Variable<Number>?,
        message: String,
        itemId: Variable<String>,
        actionType: ActionType,
        isCancelled: Variable<Boolean>?
    ): Objective

    fun createBlock(
        instruction: Instruction,
        amount: Variable<Number>?,
        message: String,
        itemId: Variable<String>,
        actionType: ActionType,
        isCancelled: Variable<Boolean>?
    ): Objective
}