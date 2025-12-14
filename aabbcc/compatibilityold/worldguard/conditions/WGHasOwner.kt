package com.github.mrjimin.betonquestaddon.compatibilityold.worldguard.conditions

import com.github.mrjimin.betonquestaddon.compatibilityold.worldguard.WGRegionCheck
import com.github.mrjimin.betonquestaddon.hook.WorldGuardHook.ownerSet
import com.sk89q.worldguard.protection.regions.ProtectedRegion
import org.betonquest.betonquest.api.instruction.variable.Variable
import org.bukkit.entity.Player

class WGHasOwner(regionName: Variable<String?>? = null) : WGRegionCheck(regionName) {
    override fun ProtectedRegion.hasPlayer(player: Player) =
        ownerSet().isNotEmpty()
}
