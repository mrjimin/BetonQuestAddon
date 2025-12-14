package com.github.mrjimin.betonquestaddon.compatibilityold.nexo.conditions

import com.nexomc.nexo.api.NexoBlocks
import org.betonquest.betonquest.api.profile.Profile
import org.betonquest.betonquest.api.quest.condition.nullable.NullableCondition
import org.betonquest.betonquest.api.instruction.variable.Variable
import org.bukkit.Location

class NxBlock(
    private val itemID: Variable<String>,
    private val location: Variable<Location>
) : NullableCondition {

    override fun check(profile: Profile?): Boolean {
        val mechanic = NexoBlocks.customBlockMechanic(location.getValue(profile)) ?: return false
        return mechanic.factory?.mechanicID == itemID.getValue(profile)
    }
}