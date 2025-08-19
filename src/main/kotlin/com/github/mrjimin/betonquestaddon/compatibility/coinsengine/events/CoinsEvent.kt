package com.github.mrjimin.betonquestaddon.compatibility.coinsengine.events

import com.github.mrjimin.betonquestaddon.hook.CoinsEngineHook
import com.github.mrjimin.betonquestaddon.hook.CoinsEngineHook.getCurrencyOrNull
import net.kyori.adventure.text.Component
import org.betonquest.betonquest.api.common.component.VariableReplacement
import org.betonquest.betonquest.api.instruction.variable.Variable
import org.betonquest.betonquest.api.profile.Profile
import org.betonquest.betonquest.api.quest.event.PlayerEvent
import org.betonquest.betonquest.quest.event.IngameNotificationSender
import kotlin.math.abs

class CoinsEvent(
    private val currencyId: String,
    private val amount: Variable<Number>,
    private val operation: Operation,
    private val givenSender: IngameNotificationSender?,
    private val takenSender: IngameNotificationSender?,
    private val resetSender: IngameNotificationSender?
) : PlayerEvent {

    enum class Operation { ADD, REMOVE, MULTIPLY, SET }

    override fun execute(profile: Profile) {
        val uuid = profile.playerUUID
        val currency = getCurrencyOrNull(currencyId)
        val current = CoinsEngineHook.getBalance(uuid, currencyId)
        val value = amount.getValue(profile).toDouble()

        val target = when (operation) {
            Operation.ADD -> current + value
            Operation.REMOVE -> current - value
            Operation.MULTIPLY -> current * value
            Operation.SET -> value
        }

        CoinsEngineHook.setBalance(uuid, currencyId, target)

        val difference = target - current
        val notification = when {
            difference > 0 -> givenSender
            difference < 0 -> takenSender
            else -> resetSender
        }

        notification?.sendNotification(
            profile,
            VariableReplacement("amount", Component.text(currency.format(abs(difference)))),
            VariableReplacement("currencyName", Component.text(currency.name)),
            VariableReplacement("currencySymbol", Component.text(currency.symbol))
        )
    }
}
