package kr.mrjimin.betonquestaddon.compatibility.coinsengine.condition

import org.betonquest.betonquest.api.QuestException
import org.betonquest.betonquest.api.instruction.Argument
import org.betonquest.betonquest.api.profile.Profile
import org.betonquest.betonquest.api.quest.condition.PlayerCondition
import org.bukkit.entity.Player
import su.nightexpress.coinsengine.api.CoinsEngineAPI

class CoinsEngineCondition(
    private val currency: Argument<String>,
    private val amount: Argument<Number>
) : PlayerCondition {

    override fun check(profile: Profile): Boolean {
        val player = profile.player as Player
        val currency = CoinsEngineAPI.getCurrency(currency.getValue(profile))
            ?: throw QuestException("Invalid CoinsEngine currency: $currency")
        val amount = amount.getValue(profile).toDouble()
        return CoinsEngineAPI.getBalance(player, currency) >= amount
    }
}