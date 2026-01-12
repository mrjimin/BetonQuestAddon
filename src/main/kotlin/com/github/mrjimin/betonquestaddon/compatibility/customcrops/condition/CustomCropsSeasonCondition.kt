package com.github.mrjimin.betonquestaddon.compatibility.customcrops.condition

import net.momirealms.customcrops.api.BukkitCustomCropsPlugin
import net.momirealms.customcrops.api.core.world.Season
import org.betonquest.betonquest.api.instruction.Argument
import org.betonquest.betonquest.api.profile.Profile
import org.betonquest.betonquest.api.quest.condition.nullable.NullableCondition
import org.bukkit.World

class CustomCropsSeasonCondition(
    private val season: Argument<Season>,
    private val world: Argument<World>
) : NullableCondition {
    override fun check(profile: Profile?): Boolean {
        val worldSeason = BukkitCustomCropsPlugin.getInstance().getWorldManager().getSeason(world.getValue(profile))
        return season.getValue(profile) == worldSeason
    }

    override fun isPrimaryThreadEnforced(): Boolean {
        return true
    }
}