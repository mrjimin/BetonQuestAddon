package com.github.mrjimin.betonquestaddon.compatibility.worldguard.condition

import org.betonquest.betonquest.api.instruction.Argument
import org.betonquest.betonquest.api.profile.Profile
import org.bukkit.World

class WGHasFlagCondition(
    private val flag: Argument<String>,
    world: Argument<World>,
    region: Argument<String>?
) : AbstractWGCondition(world, region) {
    override fun check(profile: Profile?): Boolean {
        val targetFlag = flag.getValue(profile) ?: return false
        val targetRegion = getTargetRegion(profile) ?: return false

        return targetRegion.flags.keys.any { it.name.equals(targetFlag, ignoreCase = true) }
    }
}