package com.github.mrjimin.betonquestaddon.compatibility.worldguard.conditions

import com.github.mrjimin.betonquestaddon.hook.WorldGuardHook
import com.github.mrjimin.betonquestaddon.util.toOnlinePlayer
import org.betonquest.betonquest.api.profile.Profile
import org.betonquest.betonquest.api.quest.condition.PlayerCondition
import org.betonquest.betonquest.api.instruction.variable.Variable

class WGIsOwner(
    private val regionName: Variable<String>?
) : PlayerCondition {

    override fun check(profile: Profile): Boolean {
        val player = profile.player.toOnlinePlayer()
        val region = regionName?.getValue(profile)
        return region?.let { WorldGuardHook.isOwner(player, it) } ?: WorldGuardHook.isOwnerHere(player)
    }
}