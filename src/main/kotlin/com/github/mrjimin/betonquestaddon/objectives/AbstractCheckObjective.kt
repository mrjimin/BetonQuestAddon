package com.github.mrjimin.betonquestaddon.objectives

import com.github.mrjimin.betonquestaddon.util.event.ActionType
import org.betonquest.betonquest.api.CountingObjective
import org.betonquest.betonquest.api.instruction.Instruction
import org.betonquest.betonquest.api.instruction.variable.Variable
import org.bukkit.entity.Player
import org.bukkit.event.Listener

abstract class AbstractCheckObjective(
    instruction: Instruction,
    message: String,
    amount: Variable<Number>?,
    protected val item: Variable<String>,
    protected val actionType: ActionType,
    private val isCancelled: Variable<Boolean>?
) : CountingObjective(instruction, amount, message), Listener {

    private fun dispatchIfMatches(player: Player, itemId: String) {
        val profile = profileProvider.getProfile(player)
        if (isCancelled?.getValue(profile) == true) return

        qeHandler.handle {
            if (
                containsPlayer(profile) &&
                item.getValue(profile) == itemId &&
                checkConditions(profile)
            ) {
                getCountingData(profile).progress()
                completeIfDoneOrNotify(profile)
            }
        }
    }

    protected fun handleItem(
        expected: ActionType,
        player: Player,
        itemId: String?
    ) {
        if (actionType != expected || itemId == null) return
        dispatchIfMatches(player, itemId)
    }
}

