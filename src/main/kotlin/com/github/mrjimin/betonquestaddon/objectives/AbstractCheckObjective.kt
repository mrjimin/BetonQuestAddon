//package com.github.mrjimin.betonquestaddon.objectives
//
//import com.github.mrjimin.betonquestaddon.util.event.ActionType
//import org.betonquest.betonquest.api.instruction.Instruction
//import org.betonquest.betonquest.api.instruction.Argument
//import org.betonquest.betonquest.api.profile.Profile
//import org.bukkit.entity.Player
//import org.bukkit.event.Listener
//
//abstract class AbstractCheckObjective(
//    instruction: Instruction,
//    message: String,
//    amount: Argument<Number>,
//    protected val item: Argument<String>,
//    protected val actionType: ActionType,
//    private val isCancelled: Argument<Boolean>?
//) : BaseObjective(instruction, amount, message), Listener {
//
//    override fun checkMatch(profile: Profile, input: Any?): Boolean {
//        val itemId = input as? String ?: return false
//        if (isCancelled?.getValue(profile) == true) return false
//        return item.getValue(profile) == itemId
//    }
//
//    protected fun handleItem(
//        expected: ActionType,
//        player: Player,
//        itemId: String?
//    ) {
//        if (actionType != expected || itemId == null) return
//        handle(player, itemId)
//    }
//}


package com.github.mrjimin.betonquestaddon.objectives

import com.github.mrjimin.betonquestaddon.util.event.ActionType
import org.betonquest.betonquest.api.instruction.Argument
import org.betonquest.betonquest.api.instruction.Instruction
import org.betonquest.betonquest.api.profile.Profile
import org.bukkit.entity.Player

abstract class AbstractCheckObjective(
    instruction: Instruction,
    message: String,
    amount: Argument<Number>?,
    protected val item: Argument<String>,
    private val actionType: ActionType,
    private val isCancelled: Argument<Boolean>?
) : BaseObjective(instruction, amount, message) {

    override fun checkMatch(profile: Profile, input: Any?): Boolean {
        val itemId = input as? String ?: return false

        if (isCancelled?.getValue(profile) == true) return false
        if (item.getValue(profile) != itemId) return false

        return true
    }

    protected fun handleItem(
        expected: ActionType,
        player: Player,
        itemId: String?
    ) {
        if (actionType != expected) return
        handle(player, itemId)
    }
}
