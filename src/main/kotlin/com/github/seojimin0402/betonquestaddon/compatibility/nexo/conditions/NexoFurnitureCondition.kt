package com.github.seojimin0402.betonquestaddon.compatibility.nexo.conditions

import com.nexomc.nexo.api.NexoFurniture
import org.betonquest.betonquest.api.instruction.variable.Variable
import org.betonquest.betonquest.api.profile.Profile
import org.betonquest.betonquest.api.quest.condition.nullable.NullableCondition
import org.bukkit.Location

class NexoFurnitureCondition(
    private val itemId: Variable<String>,
    private val location: Variable<Location>
) : NullableCondition {

    override fun check(profile: Profile?): Boolean {
        val mechanic = NexoFurniture.furnitureMechanic(location.getValue(profile)) ?: return false
        return mechanic.factory.mechanicID == itemId.getValue(profile)
    }

}