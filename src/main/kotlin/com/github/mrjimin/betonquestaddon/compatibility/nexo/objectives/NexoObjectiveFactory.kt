package com.github.mrjimin.betonquestaddon.compatibility.nexo.objectives

import com.github.mrjimin.betonquestaddon.objectives.ICheckObjectiveFactory
import com.github.mrjimin.betonquestaddon.util.event.ActionType
import com.github.mrjimin.betonquestaddon.util.event.TargetType
import org.betonquest.betonquest.api.Objective
import org.betonquest.betonquest.api.instruction.Instruction
import org.betonquest.betonquest.api.instruction.variable.Variable

class NexoObjectiveFactory(
    override val targetType: TargetType,
    override val actionType: ActionType
) : ICheckObjectiveFactory {

    override fun createFurniture(
        instruction: Instruction,
        amount: Variable<Number>?,
        message: String,
        itemId: Variable<String>,
        actionType: ActionType,
        isCancelled: Variable<Boolean>?
    ): Objective = NexoFurnitureObjective(
        instruction,
        amount,
        message,
        itemId,
        actionType,
        isCancelled
    )

    override fun createBlock(
        instruction: Instruction,
        amount: Variable<Number>?,
        message: String,
        itemId: Variable<String>,
        actionType: ActionType,
        isCancelled: Variable<Boolean>?
    ): Objective = NexoBlockObjective(
        instruction,
        amount,
        message,
        itemId,
        actionType,
        isCancelled
    )

}