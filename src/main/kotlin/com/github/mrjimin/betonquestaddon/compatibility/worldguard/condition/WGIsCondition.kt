package com.github.mrjimin.betonquestaddon.compatibility.worldguard.condition

import com.github.mrjimin.betonquestaddon.compatibility.worldguard.TargetType
import com.github.mrjimin.betonquestaddon.compatibility.worldguard.WorldGuardUtil
import org.betonquest.betonquest.api.profile.Profile
import org.betonquest.betonquest.api.quest.condition.PlayerCondition
import org.bukkit.entity.Player

class WGIsCondition(
    private val targetType: TargetType,
) : PlayerCondition {

    override fun check(profile: Profile): Boolean {
        val player = profile.player as? Player ?: return false
        val region = WorldGuardUtil.getRegionByLocation(player.location) ?: return false
        return when (targetType) {
            TargetType.OWNER -> region.owners.contains(player.uniqueId) || region.owners.contains(player.name)
            TargetType.MEMBER -> region.members.contains(player.uniqueId) || region.members.contains(player.name)
        }

    }
}