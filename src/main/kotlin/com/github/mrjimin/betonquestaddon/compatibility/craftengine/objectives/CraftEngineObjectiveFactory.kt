package com.github.mrjimin.betonquestaddon.compatibility.craftengine.objectives

import com.github.mrjimin.betonquestaddon.objectives.ICheckObjectiveFactory
import com.github.mrjimin.betonquestaddon.util.event.ActionType
import com.github.mrjimin.betonquestaddon.util.event.TargetType
import org.betonquest.betonquest.api.DefaultObjective
import org.betonquest.betonquest.api.instruction.Instruction
import org.betonquest.betonquest.api.instruction.Argument

class CraftEngineObjectiveFactory(
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
    ): DefaultObjective = CraftEngineFurnitureObjective(
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
    ): DefaultObjective = CraftEngineBlockObjective(
        instruction,
        amount,
        message,
        itemId,
        actionType,
        isCancelled
    )

}