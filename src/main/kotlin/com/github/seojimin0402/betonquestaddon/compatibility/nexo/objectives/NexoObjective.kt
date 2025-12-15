package com.github.seojimin0402.betonquestaddon.compatibility.nexo.objectives

import com.github.seojimin0402.betonquestaddon.util.event.ActionType
import com.github.seojimin0402.betonquestaddon.util.event.TargetType
import org.betonquest.betonquest.api.CountingObjective
import org.betonquest.betonquest.api.instruction.Instruction
import org.betonquest.betonquest.api.instruction.variable.Variable
import org.bukkit.entity.Player

class NexoObjective(
    instruction: Instruction,
    targetAmount: Variable<Number>?,
    message: String,
    private val item: Variable<String>,
    private val action: ActionType,
    private val target: TargetType
) : CountingObjective(instruction, targetAmount, message) {

    fun tryProgress(player: Player, itemId: String) {
        val profile = profileProvider.getProfile(player)

        qeHandler.handle {
            if (!containsPlayer(profile)) return@handle
            if (!checkConditions(profile)) return@handle
            if (item.getValue(profile) != itemId) return@handle

            getCountingData(profile).progress()
            completeIfDoneOrNotify(profile)
        }
    }

    fun matches(action: ActionType, target: TargetType): Boolean =
        this.action == action && this.target == target
}
