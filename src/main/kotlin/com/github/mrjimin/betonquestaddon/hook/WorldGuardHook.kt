package com.github.mrjimin.betonquestaddon.hook

import com.github.mrjimin.betonquestaddon.util.server.NotFoundPlugin
import com.github.mrjimin.betonquestaddon.util.server.checkPlugin
import com.sk89q.worldedit.bukkit.BukkitAdapter
import com.sk89q.worldguard.WorldGuard
import com.sk89q.worldguard.protection.regions.ProtectedRegion
import org.bukkit.Bukkit
import org.bukkit.Location
import org.bukkit.entity.Player

object WorldGuardHook {

    init {
        if (!"WorldGuard".checkPlugin()) throw NotFoundPlugin("WorldGuard")
    }

    private fun getRegionManager(world: org.bukkit.World?) =
        world?.let { WorldGuard.getInstance().platform.regionContainer[BukkitAdapter.adapt(it)] }

    fun getRegionByName(regionId: String): ProtectedRegion? {
        val container = WorldGuard.getInstance().platform.regionContainer
        for (world in Bukkit.getWorlds()) {
            val manager = container[BukkitAdapter.adapt(world)] ?: continue
            val region = manager.getRegion(regionId)
            if (region != null) return region
        }
        return null
    }

    fun getRegionByName(player: Player, regionId: String) =
        getRegionManager(player.world)?.getRegion(regionId)

    fun getApplicableRegions(location: Location) =
        getRegionManager(location.world)
            ?.getApplicableRegions(BukkitAdapter.asBlockVector(location))
            ?.regions ?: emptySet()

    fun isOwner(player: Player, regionId: String) =
        getRegionByName(player, regionId)?.ownerSet()?.let { player.name in it || player.uniqueId.toString() in it } == true

    fun isMember(player: Player, regionId: String) =
        getRegionByName(player, regionId)?.memberSet()?.let { player.name in it || player.uniqueId.toString() in it } == true

    fun isOwnerHere(player: Player) =
        getApplicableRegions(player.location).any { player.name in it.ownerSet() || player.uniqueId.toString() in it.ownerSet() }

    fun isMemberHere(player: Player) =
        getApplicableRegions(player.location).any { player.name in it.memberSet() || player.uniqueId.toString() in it.memberSet() }

    fun ProtectedRegion.ownerSet() = (owners.players + owners.uniqueIds.map { it.toString() }).toSet()
    fun ProtectedRegion.memberSet() = (members.players + members.uniqueIds.map { it.toString() }).toSet()
}
