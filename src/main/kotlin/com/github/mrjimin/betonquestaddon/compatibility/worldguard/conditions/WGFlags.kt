package com.github.mrjimin.betonquestaddon.compatibility.worldguard.conditions

import com.github.mrjimin.betonquestaddon.hook.WorldGuardHook
import com.github.mrjimin.betonquestaddon.util.checkHas
import com.github.mrjimin.betonquestaddon.util.toOnlinePlayer
import org.betonquest.betonquest.api.profile.Profile
import org.betonquest.betonquest.api.quest.condition.PlayerCondition
import org.betonquest.betonquest.api.instruction.variable.Variable

class WGFlags(
    private val regionName: Variable<String>,
    private val has: Variable<String>,
    private val flags: Variable<Set<String>>
) : PlayerCondition {

    override fun check(profile: Profile): Boolean {
        val player = profile.player.toOnlinePlayer()
        val region = regionName.getValue(profile)
        val mode = has.getValue(profile)

        val regionFlags = WorldGuardHook.flags(player, region).map { it.lowercase() }.toSet()
        val required = flags.getValue(profile).map { it.lowercase() }

        return required.map { it in regionFlags }.checkHas(mode)
    }
}
