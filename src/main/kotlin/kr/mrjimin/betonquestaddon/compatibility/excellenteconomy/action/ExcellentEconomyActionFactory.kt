package kr.mrjimin.betonquestaddon.compatibility.excellenteconomy.action

import kr.mrjimin.betonquestaddon.compatibility.excellenteconomy.ExcellentEconomyActionType
import org.betonquest.betonquest.api.instruction.Instruction
import org.betonquest.betonquest.api.quest.action.PlayerAction
import org.betonquest.betonquest.api.quest.action.PlayerActionFactory
import su.nightexpress.excellenteconomy.api.ExcellentEconomyAPI

class ExcellentEconomyActionFactory(
    private val excellentEconomyApi: ExcellentEconomyAPI,
) : PlayerActionFactory {
    override fun parsePlayer(instruction: Instruction): PlayerAction {
        val actionType = instruction.enumeration(ExcellentEconomyActionType::class.java).get()
        val currency = instruction.string().get()
        val amount = instruction.number().get()
        return ExcellentEconomyAction(excellentEconomyApi, actionType, currency, amount)
    }
}