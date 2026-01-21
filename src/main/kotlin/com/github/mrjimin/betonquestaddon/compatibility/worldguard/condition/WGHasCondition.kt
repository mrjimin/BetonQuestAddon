package com.github.mrjimin.betonquestaddon.compatibility.worldguard.condition

import com.github.mrjimin.betonquestaddon.compatibility.worldguard.TargetType
import com.github.mrjimin.betonquestaddon.compatibility.worldguard.WorldGuardUtil
import org.betonquest.betonquest.api.instruction.Argument
import org.betonquest.betonquest.api.profile.Profile
import org.betonquest.betonquest.api.quest.condition.nullable.NullableCondition
import org.bukkit.World
import org.bukkit.entity.Player

class WGHasCondition(
    private val targetType: TargetType,
    private val world: Argument<World>,
    private val region: Argument<String>?
): NullableCondition {

    override fun check(profile: Profile?): Boolean {
        val targetWorld = world.getValue(profile) ?: return false

        val targetRegion = if (region != null) {
            val id = region.getValue(profile) ?: return false
            WorldGuardUtil.getRegionByName(targetWorld, id)
        } else {
            val player = profile?.player as? Player ?: return false
            WorldGuardUtil.getRegionByLocation(player.location)
        }

        return when (targetType) {
            TargetType.OWNER -> targetRegion?.owners?.size()?.let { it > 0 } ?: false
            TargetType.MEMBER -> targetRegion?.members?.size()?.let { it > 0 } ?: false
        }
    }

    override fun isPrimaryThreadEnforced(): Boolean = true
}