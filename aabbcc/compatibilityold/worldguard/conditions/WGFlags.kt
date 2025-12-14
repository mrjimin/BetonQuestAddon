package com.github.mrjimin.betonquestaddon.compatibilityold.worldguard.conditions

import com.github.mrjimin.betonquestaddon.hook.WorldGuardHook
import com.github.mrjimin.betonquestaddon.util.checkHas
import com.github.mrjimin.betonquestaddon.util.toOnlinePlayer
import org.betonquest.betonquest.api.profile.Profile
import org.betonquest.betonquest.api.instruction.variable.Variable
import org.betonquest.betonquest.api.quest.condition.PlayerCondition

//class WGFlags(
//    private val world: Variable<World>,
//    private val regionName: Variable<String>,
//    private val has: Variable<String>,
//    private val flags: Variable<Set<String>>
//) : NullableCondition {
//
//    override fun check(profile: Profile?): Boolean {
//        val world = world.getValue(profile)
//        val region = regionName.getValue(profile)
//        val mode = has.getValue(profile)
//
//        val wgRegion = WorldGuardHook.getRegionByName(world, region) ?: return false
//
//        val regionFlags = wgRegion.flags.keys.map { it.name.lowercase() }.toSet()
//        val required = flags.getValue(profile).map { it.lowercase() }
//
//        return required.map { it in regionFlags }.checkHas(mode)
//    }
//}

class WGFlags(
    private val regionName: Variable<String>,
    private val has: Variable<String>,
    private val flags: Variable<Set<String>>
) : PlayerCondition {

    override fun check(profile: Profile): Boolean {
        val player = profile.player.toOnlinePlayer()
        val regionId = regionName.getValue(profile)
        val mode = has.getValue(profile)

        val region = WorldGuardHook.getRegionByName(player, regionId) ?: return false

        val regionFlags = region.flags.keys.map { it.name.lowercase() }.toSet()
        val requiredFlags = flags.getValue(profile).map { it.lowercase() }

        return requiredFlags.map { it in regionFlags }.checkHas(mode)
    }
}
