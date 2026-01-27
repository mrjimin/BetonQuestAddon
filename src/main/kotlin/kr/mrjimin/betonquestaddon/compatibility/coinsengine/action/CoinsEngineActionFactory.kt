package kr.mrjimin.betonquestaddon.compatibility.coinsengine.action

import kr.mrjimin.betonquestaddon.compatibility.coinsengine.CoinsEngineActionType
import org.betonquest.betonquest.api.instruction.Instruction
import org.betonquest.betonquest.api.quest.action.PlayerAction
import org.betonquest.betonquest.api.quest.action.PlayerActionFactory

class CoinsEngineActionFactory : PlayerActionFactory {
    override fun parsePlayer(instruction: Instruction): PlayerAction {
        val actionType = instruction.enumeration(CoinsEngineActionType::class.java).get()
        val currency = instruction.string().get()
        val amount = instruction.number().get()
        return CoinsEngineAction(actionType, currency, amount)
    }
}