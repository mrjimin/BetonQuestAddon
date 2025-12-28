package com.github.mrjimin.betonquestaddon.compatibility.itemsadder.objectives

import com.github.mrjimin.betonquestaddon.objectives.ICheckObjectiveFactory
import com.github.mrjimin.betonquestaddon.util.event.ActionType
import com.github.mrjimin.betonquestaddon.util.event.TargetType
import org.betonquest.betonquest.api.Objective
import org.betonquest.betonquest.api.instruction.Instruction
import org.betonquest.betonquest.api.instruction.Argument

class ItemsAdderObjectiveFactory(
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
    ): Objective = ItemsAdderFurnitureObjective(
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
    ): Objective = ItemsAdderBlockObjective(
        instruction,
        amount,
        message,
        itemId,
        actionType,
        isCancelled
    )
}