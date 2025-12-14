package com.github.mrjimin.betonquestaddon.compatibilityold.advancedenchantments.conditions

import com.github.mrjimin.betonquestaddon.hook.PAPIHook.resolvePapiRaw
import com.github.mrjimin.betonquestaddon.util.compareWith
import com.github.mrjimin.betonquestaddon.util.toOnlinePlayer
import org.betonquest.betonquest.api.instruction.variable.Variable
import org.betonquest.betonquest.api.profile.Profile
import org.betonquest.betonquest.api.quest.condition.PlayerCondition

class AEHasCECondition(
    private val group: Variable<String>,
    private val operator: Variable<String>,
    private val amount: Variable<Number>
) : PlayerCondition {

    override fun check(profile: Profile): Boolean {
        val groupValue = group.getValue(profile).lowercase()
        val op = operator.getValue(profile)
        val requiredAmount = amount.getValue(profile).toInt()
        val player = profile.player.toOnlinePlayer()

        val placeholder = if (groupValue == "*") {
            "%advancedenchantments_item_enchants_total%"
        } else {
            "%advancedenchantments_item_enchants_total_$groupValue%"
        }

        val papiValue = placeholder.resolvePapiRaw(player).toIntOrNull() ?: return false

        return papiValue.compareWith(op, requiredAmount)
    }
}