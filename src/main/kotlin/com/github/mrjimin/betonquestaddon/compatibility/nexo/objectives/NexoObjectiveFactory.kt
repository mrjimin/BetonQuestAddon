package com.github.mrjimin.betonquestaddon.compatibility.nexo.objectives

import com.github.mrjimin.betonquestaddon.objectives.ICheckObjectiveFactory
import com.github.mrjimin.betonquestaddon.util.action.ActionType
import com.github.mrjimin.betonquestaddon.util.action.TargetType
import org.betonquest.betonquest.api.DefaultObjective
import org.betonquest.betonquest.api.instruction.Instruction
import org.betonquest.betonquest.api.instruction.Argument

class NexoObjectiveFactory(
    override val targetType: TargetType,
    override val actionType: ActionType
) : ICheckObjectiveFactory {

    override fun createFurniture(
        instruction: Instruction,
        amount: Argument<Number>,
        message: String,
        itemId: Argument<String>,
        actionType: ActionType,
        isCancelled: Argument<Boolean>?
    ): DefaultObjective = NexoFurnitureObjective(
        instruction,
        amount,
        message,
        itemId,
        actionType,
        isCancelled
    )

    override fun createBlock(
        instruction: Instruction,
        amount: Argument<Number>,
        message: String,
        itemId: Argument<String>,
        actionType: ActionType,
        isCancelled: Argument<Boolean>?
    ): DefaultObjective = NexoBlockObjective(
        instruction,
        amount,
        message,
        itemId,
        actionType,
        isCancelled
    )

}