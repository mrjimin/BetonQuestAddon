package com.github.seojimin0402.betonquestaddon.compatibility.nexo.conditions

import org.betonquest.betonquest.api.instruction.variable.Variable
import org.betonquest.betonquest.api.profile.Profile
import org.betonquest.betonquest.api.quest.condition.nullable.NullableCondition
import org.bukkit.Location

class NexoCondition(
    private val itemId: Variable<String>,
    private val location: Variable<Location>,
    private val mechanicIdProvider: (Location) -> String?
) : NullableCondition {

    override fun check(profile: Profile?): Boolean {
        val loc = location.getValue(profile)
        val mechanicId = mechanicIdProvider(loc) ?: return false
        return mechanicId == itemId.getValue(profile)
    }
}
