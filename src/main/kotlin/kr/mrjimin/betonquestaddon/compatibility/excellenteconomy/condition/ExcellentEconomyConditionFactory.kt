package kr.mrjimin.betonquestaddon.compatibility.excellenteconomy.condition

import org.betonquest.betonquest.api.instruction.Instruction
import org.betonquest.betonquest.api.quest.condition.PlayerCondition
import org.betonquest.betonquest.api.quest.condition.PlayerConditionFactory
import su.nightexpress.excellenteconomy.api.ExcellentEconomyAPI

class ExcellentEconomyConditionFactory(
    private val excellentEconomyAPI: ExcellentEconomyAPI
) : PlayerConditionFactory {
    override fun parsePlayer(instruction: Instruction): PlayerCondition {
        val currency = instruction.string().get()
        val amount = instruction.number().get()
        return ExcellentEconomyCondition(excellentEconomyAPI, currency, amount)
    }
}