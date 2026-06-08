package kr.mrjimin.betonquestaddon.util

import org.betonquest.betonquest.api.instruction.Argument
import org.betonquest.betonquest.api.profile.Profile
import org.bukkit.Location

data class LocationFilter(
    val location: Argument<Location>,
    val range: Argument<Number>
) {
    fun matches(profile: Profile, target: Location): Boolean {
        val base = location.getValue(profile)
        val r = range.getValue(profile).toDouble()

        return base.world == target.world &&
                base.distanceSquared(target) <= r * r
    }
}