package kr.mrjimin.betonquestaddon.compatibility.coinsengine.condition

import org.betonquest.betonquest.api.instruction.Instruction
import org.betonquest.betonquest.api.quest.condition.PlayerCondition
import org.betonquest.betonquest.api.quest.condition.PlayerConditionFactory

class CoinsEngineConditionFactory : PlayerConditionFactory {

    override fun parsePlayer(instruction: Instruction): PlayerCondition {
        val currency = instruction.string().get()
        val amount = instruction.number().get()
        return CoinsEngineCondition(currency, amount)
    }
}