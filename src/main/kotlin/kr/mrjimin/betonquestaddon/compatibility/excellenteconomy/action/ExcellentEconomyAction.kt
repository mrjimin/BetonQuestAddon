package kr.mrjimin.betonquestaddon.compatibility.excellenteconomy.action

import kr.mrjimin.betonquestaddon.compatibility.excellenteconomy.ExcellentEconomyActionType
import org.betonquest.betonquest.api.QuestException
import org.betonquest.betonquest.api.instruction.Argument
import org.betonquest.betonquest.api.profile.Profile
import org.betonquest.betonquest.api.quest.action.PlayerAction
import org.bukkit.entity.Player
import su.nightexpress.excellenteconomy.api.ExcellentEconomyAPI

class ExcellentEconomyAction(
    private val excellentEconomyApi: ExcellentEconomyAPI,
    private val actionType: Argument<ExcellentEconomyActionType>,
    private val currency: Argument<String>,
    private val amount: Argument<Number>
) : PlayerAction {

    override fun execute(profile: Profile) {
        val player = profile.player as Player
        val currency = excellentEconomyApi.getCurrency(currency.getValue(profile))
            ?: throw QuestException("Invalid CoinsEngine currency: $currency")
        val amount = amount.getValue(profile).toDouble()

        when (actionType.getValue(profile)) {
            ExcellentEconomyActionType.SET -> {
                excellentEconomyApi.setBalance(player, currency, amount)
            }
            ExcellentEconomyActionType.ADD -> {
                excellentEconomyApi.deposit(player, currency, amount)
            }
            ExcellentEconomyActionType.REMOVE -> {
                excellentEconomyApi.withdraw(player, currency, amount)
            }
            ExcellentEconomyActionType.MULTIPLY -> {
                val newBalance = excellentEconomyApi.getBalance(player, currency) * amount
                excellentEconomyApi.setBalance(player, currency, newBalance)
            }
        }
    }

}