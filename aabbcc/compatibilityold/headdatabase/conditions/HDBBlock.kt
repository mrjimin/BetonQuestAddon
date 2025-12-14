package com.github.mrjimin.betonquestaddon.compatibilityold.headdatabase.conditions

import com.github.mrjimin.betonquestaddon.hook.HDBHook
import org.betonquest.betonquest.api.profile.Profile
import org.betonquest.betonquest.api.quest.condition.nullable.NullableCondition
import org.betonquest.betonquest.api.instruction.variable.Variable
import org.bukkit.Location

class HDBBlock(
    private val itemID: Variable<String>,
    private val location: Variable<Location>
) : NullableCondition {

    override fun check(profile: Profile?): Boolean {
        return HDBHook.getHDBId(location.getValue(profile)) == itemID.getValue(profile)
    }
}