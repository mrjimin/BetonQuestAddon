package com.github.mrjimin.betonquestaddon.compatibility.coinsengine.conditions

import com.github.mrjimin.betonquestaddon.hook.CoinsEngineHook
import com.github.mrjimin.betonquestaddon.util.compareWith
import org.betonquest.betonquest.api.instruction.variable.Variable
import org.betonquest.betonquest.api.profile.Profile
import org.betonquest.betonquest.api.quest.condition.PlayerCondition

class CoinsCondition(
    private val currencyId: Variable<String>,
    private val operator: Variable<String>,
    private val amount: Variable<Number>
) : PlayerCondition {

    override fun check(profile: Profile): Boolean {
        val userBalance = CoinsEngineHook.getBalance(profile.player.uniqueId, currencyId.getValue(profile))
        val op = operator.getValue(profile)
        val requiredAmount = amount.getValue(profile).toDouble()
        return userBalance.compareWith(op, requiredAmount)
    }
}