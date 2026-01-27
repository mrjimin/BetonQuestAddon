package kr.mrjimin.betonquestaddon.compatibility.customnameplates.condition

import kr.mrjimin.betonquestaddon.compatibility.customnameplates.NpCheckType
import org.betonquest.betonquest.api.instruction.Instruction
import org.betonquest.betonquest.api.quest.condition.PlayerCondition
import org.betonquest.betonquest.api.quest.condition.PlayerConditionFactory

class NpHasConditionFactory(
    private val type: NpCheckType
): PlayerConditionFactory {
    override fun parsePlayer(instruction: Instruction): PlayerCondition {
        val target = instruction.string().get()
        val ignore = instruction.bool().get("ignore", false)
        return NpHasCondition(type, target, ignore)
    }
}