package com.github.mrjimin.betonquestaddon.compatibilityold.worldguard

import com.github.mrjimin.betonquestaddon.hook.WorldGuardHook
import com.github.mrjimin.betonquestaddon.util.toOnlinePlayer
import com.sk89q.worldguard.protection.regions.ProtectedRegion
import org.betonquest.betonquest.api.instruction.variable.Variable
import org.betonquest.betonquest.api.profile.Profile
import org.betonquest.betonquest.api.quest.condition.PlayerCondition
import org.bukkit.entity.Player

abstract class WGRegionCheck(
    private val regionName: Variable<String?>?
) : PlayerCondition {
    protected abstract fun ProtectedRegion.hasPlayer(player: Player): Boolean

    override fun check(profile: Profile): Boolean {
        val player: Player = profile.player.toOnlinePlayer()

        val regions = regionName?.getValue(profile)
            ?.let { WorldGuardHook.getRegionByName(player, it)?.let { listOf(it) } }
            ?: WorldGuardHook.getApplicableRegions(player.location)

        return regions.any { it.hasPlayer(player) }
    }
}
