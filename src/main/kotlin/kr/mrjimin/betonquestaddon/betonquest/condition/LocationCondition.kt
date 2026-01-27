package kr.mrjimin.betonquestaddon.betonquest.condition

import org.betonquest.betonquest.api.instruction.Argument
import org.betonquest.betonquest.api.profile.Profile
import org.betonquest.betonquest.api.quest.condition.nullable.NullableCondition
import org.bukkit.Location

class LocationCondition(
    private val itemId: Argument<String>,
    private val location: Argument<Location>,
    private val mechanicIdProvider: (Location) -> String?
) : NullableCondition {

    override fun check(profile: Profile?): Boolean {
        val loc = location.getValue(profile)
        val mechanicId = mechanicIdProvider(loc) ?: return false
        return mechanicId == itemId.getValue(profile)
    }

    override fun isPrimaryThreadEnforced(): Boolean = true
}
