package com.github.mrjimin.betonquestaddon.hook

import com.github.mrjimin.betonquestaddon.util.server.NotFoundPlugin
import com.github.mrjimin.betonquestaddon.util.server.checkPlugin
import com.sk89q.worldedit.bukkit.BukkitAdapter
import com.sk89q.worldguard.WorldGuard
import com.sk89q.worldguard.protection.flags.Flag
import com.sk89q.worldguard.protection.managers.RegionManager
import com.sk89q.worldguard.protection.regions.ProtectedRegion
import org.bukkit.Location
import org.bukkit.World
import org.bukkit.entity.Entity
import org.bukkit.entity.Player

object WorldGuardHook {

    init {
        if (!"WorldGuard".checkPlugin()) throw NotFoundPlugin("WorldGuard")
    }

    private fun getRegionManager(world: World?): RegionManager? =
        world?.let { WorldGuard.getInstance().platform.regionContainer[BukkitAdapter.adapt(it)] }

    fun getRegionManager(location: Location): RegionManager? =
        getRegionManager(location.world)

    private fun ProtectedRegion.ownerSet() = (owners.players + owners.uniqueIds.map { it.toString() }).toSet()
    private fun ProtectedRegion.memberSet() = (members.players + members.uniqueIds.map { it.toString() }).toSet()
    private fun ProtectedRegion.flagSet() = flags.keys.map { it.name }.toSet()

    fun addOwner(player: Player, regionId: String, targetPlayer: Player) {
        getRegionManager(player.location)?.getRegion(regionId)?.owners?.addPlayer(targetPlayer.uniqueId)
    }

    fun addMember(player: Player, regionId: String, targetPlayer: Player) {
        getRegionManager(player.location)?.getRegion(regionId)?.members?.addPlayer(targetPlayer.uniqueId)
    }

    @Suppress("UNCHECKED_CAST")
    fun <V> setFlag(player: Player, regionId: String, flagName: String, value: V) {
        val region = getRegionManager(player.location)?.getRegion(regionId) ?: return
        val registry = WorldGuard.getInstance().flagRegistry
        val flag = registry.get(flagName) as? Flag<V> ?: return

        region.setFlag(flag, value)
    }

    fun setFlags(player: Player, regionId: String, flags: Map<String, Any>) {
        flags.forEach { (name, value) ->
            setFlag(player, regionId, name, value)
        }
    }

    private fun getRegionProperty(player: Player, selector: ProtectedRegion.() -> Set<String>): Set<String> {
        val manager = getRegionManager(player.location) ?: return emptySet()
        return manager.getApplicableRegions(BukkitAdapter.asBlockVector(player.location))
            .regions.flatMap { it.selector() }
            .toSet()
    }

    fun ownerFromRegion(player: Player) = getRegionProperty(player) { ownerSet() }
    fun memberFromRegion(player: Player) = getRegionProperty(player) { memberSet() }
    fun flagsFromRegion(player: Player) = getRegionProperty(player) { flagSet() }

    private fun isInSet(player: Player, set: Set<String>) =
        player.name in set || player.uniqueId.toString() in set

    fun isOwnerHere(player: Player) = isInSet(player, ownerFromRegion(player))
    fun isMemberHere(player: Player) = isInSet(player, memberFromRegion(player))

    fun isOwner(player: Player, regionId: String) =
        getRegionManager(player.location)?.getRegion(regionId)?.ownerSet()?.let { isInSet(player, it) } == true

    fun isMember(player: Player, regionId: String) =
        getRegionManager(player.location)?.getRegion(regionId)?.memberSet()?.let { isInSet(player, it) } == true

    fun getRegions(location: Location) =
        getRegionManager(location)?.getApplicableRegions(BukkitAdapter.adapt(location).toVector().toBlockPoint())?.regions
            ?: emptySet()

    fun flags(player: Player, regionId: String): Set<String> {
        val region = getRegionManager(player.location)?.getRegion(regionId) ?: return emptySet()
        return region.flagSet()
    }

    fun isInRegion(location: Location, region: String) =
        getRegions(location).any { it.id.equals(region, ignoreCase = true) }

    fun isInRegion(location: Location, regions: Collection<String>) =
        getRegions(location).any { it.id in regions }

    fun isInRegion(entity: Entity, regions: Collection<String>) =
        isInRegion(entity.location, regions)

    fun hasExited(from: Location, to: Location, region: String) =
        isInRegion(from, region) && !isInRegion(to, region)

    fun hasExited(from: Location, to: Location, regions: Collection<String>) =
        isInRegion(from, regions) && !isInRegion(to, regions)
}
