package kr.mrjimin.betonquestaddon.compatibility.coinsengine.action

import kr.mrjimin.betonquestaddon.compatibility.coinsengine.CoinsEngineActionType
import org.betonquest.betonquest.api.QuestException
import org.betonquest.betonquest.api.instruction.Argument
import org.betonquest.betonquest.api.profile.Profile
import org.betonquest.betonquest.api.quest.action.PlayerAction
import org.bukkit.entity.Player
import su.nightexpress.coinsengine.api.CoinsEngineAPI

class CoinsEngineAction(
    private val actionType: Argument<CoinsEngineActionType>,
    private val currency: Argument<String>,
    private val amount: Argument<Number>
) : PlayerAction {

    override fun execute(profile: Profile) {
        val player = profile.player as Player
        val currency = CoinsEngineAPI.getCurrency(currency.getValue(profile))
            ?: throw QuestException("Invalid CoinsEngine currency: $currency")
        val amount = amount.getValue(profile).toDouble()

        when (actionType.getValue(profile)) {
            CoinsEngineActionType.SET -> {
                CoinsEngineAPI.setBalance(player, currency, amount)
            }
            CoinsEngineActionType.ADD -> {
                CoinsEngineAPI.addBalance(player, currency, amount)
            }
            CoinsEngineActionType.REMOVE -> {
                CoinsEngineAPI.removeBalance(player, currency, amount)
            }
            CoinsEngineActionType.MULTIPLY -> {
                val newBalance = CoinsEngineAPI.getBalance(player, currency) * amount
                CoinsEngineAPI.setBalance(player, currency, newBalance)
            }
        }
    }

}