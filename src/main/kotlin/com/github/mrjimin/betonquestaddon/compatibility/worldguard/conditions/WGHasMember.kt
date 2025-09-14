package com.github.mrjimin.betonquestaddon.compatibility.worldguard.conditions

import com.github.mrjimin.betonquestaddon.compatibility.worldguard.WGRegionCheck
import com.github.mrjimin.betonquestaddon.hook.WorldGuardHook.memberSet
import com.sk89q.worldguard.protection.regions.ProtectedRegion
import org.betonquest.betonquest.api.instruction.variable.Variable
import org.bukkit.entity.Player

class WGHasMember(regionName: Variable<String?>? = null) : WGRegionCheck(regionName) {
    override fun ProtectedRegion.hasPlayer(player: Player) =
        memberSet().isNotEmpty()
}