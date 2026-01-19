package com.github.mrjimin.betonquestaddon.betonquest.objective

import com.github.mrjimin.betonquestaddon.config.NotifyMessage
import com.github.mrjimin.betonquestaddon.util.action.Action
import org.betonquest.betonquest.api.instruction.Argument
import org.betonquest.betonquest.api.instruction.Instruction
import org.betonquest.betonquest.api.quest.objective.ObjectiveFactory
import org.bukkit.Location

abstract class AbstractAddonObjectiveFactory(
    protected val action: Action,
    protected val notifyMessage: NotifyMessage
) : ObjectiveFactory {

    protected data class AddonObjectiveArgs(
        val ids: Argument<List<String>>,
        val amount: Argument<Number>,
        val isCancelled: Argument<Boolean>,
        val location: Argument<Location>?,
        val range: Argument<Number>?
    )

    protected fun parseBaseArgs(instruction: Instruction): AddonObjectiveArgs {
        return AddonObjectiveArgs(
            ids = instruction.string().list().get(),
            amount = instruction.number().get("amount", 1),
            isCancelled = instruction.bool().get("isCancelled", false),
            location = instruction.location().get("location").orElse(null),
            range = instruction.number().get("range", 1)
        )
    }

}