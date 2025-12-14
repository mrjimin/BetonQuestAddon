package com.github.mrjimin.betonquestaddon.compatibilityold.craftengine.conditions

import com.github.mrjimin.betonquestaddon.hook.CraftEngineHook
import com.github.mrjimin.betonquestaddon.hook.CraftEngineHook.toIdOrNull
import org.betonquest.betonquest.api.instruction.variable.Variable
import org.betonquest.betonquest.api.profile.Profile
import org.betonquest.betonquest.api.quest.condition.nullable.NullableCondition
import org.bukkit.Location

class CeBlock(
    private val itemID: Variable<String>,
    private val location: Variable<Location>
) : NullableCondition {

    override fun check(profile: Profile?): Boolean {
        val mechanic = CraftEngineHook.customBlockAdapt(location.getValue(profile)) ?: return false
        return mechanic.customBlock()?.id().toIdOrNull() == itemID.getValue(profile)
    }
}