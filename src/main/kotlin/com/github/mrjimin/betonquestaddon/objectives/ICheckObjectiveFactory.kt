package com.github.mrjimin.betonquestaddon.objectives

import com.github.mrjimin.betonquestaddon.util.event.ActionType
import com.github.mrjimin.betonquestaddon.util.event.TargetType
import org.betonquest.betonquest.api.DefaultObjective
import org.betonquest.betonquest.api.instruction.Instruction
import org.betonquest.betonquest.api.instruction.Argument
import org.betonquest.betonquest.api.quest.objective.ObjectiveFactory

interface ICheckObjectiveFactory : ObjectiveFactory {

    val targetType: TargetType
    val actionType: ActionType

    override fun parseInstruction(instruction: Instruction): DefaultObjective {
        val itemId = instruction.string().get()
        val amount = instruction.number().get("amount", 1)

        val isCancelled = instruction.bool().get("isCancelled", false)

        val message =
            "betonQuestAddon.${targetType.name.lowercase()}.${actionType.name.lowercase()}"

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
        amount: Argument<Number>,
        message: String,
        itemId: Argument<String>,
        actionType: ActionType,
        isCancelled: Argument<Boolean>?
    ): DefaultObjective

    fun createBlock(
        instruction: Instruction,
        amount: Argument<Number>,
        message: String,
        itemId: Argument<String>,
        actionType: ActionType,
        isCancelled: Argument<Boolean>?
    ): DefaultObjective
}