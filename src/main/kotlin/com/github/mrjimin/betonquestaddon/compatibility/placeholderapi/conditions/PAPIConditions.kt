package com.github.mrjimin.betonquestaddon.compatibility.placeholderapi.conditions

import com.github.mrjimin.betonquestaddon.hook.PAPIHook.toPapi
import com.github.mrjimin.betonquestaddon.util.compareWith
import com.github.mrjimin.betonquestaddon.util.toOnlinePlayer
import org.betonquest.betonquest.api.profile.Profile
import org.betonquest.betonquest.api.quest.condition.PlayerCondition
import org.betonquest.betonquest.api.instruction.variable.Variable

class PAPIConditions(
    private val first: Variable<String>,
    private val operator: Variable<String>,
    private val second: Variable<String>,
    private val ignoreCase: Variable<Boolean>,
) : PlayerCondition {

    override fun check(profile: Profile): Boolean {
        val firstValue = first.getValue(profile).toPapi(profile.player.toOnlinePlayer())
        val operatorValue = operator.getValue(profile)
        val secondValue = second.getValue(profile)
        val ignoreCaseValue = ignoreCase.getValue(profile)

        return firstValue.compareWith(operatorValue, secondValue, ignoreCaseValue)
    }
}