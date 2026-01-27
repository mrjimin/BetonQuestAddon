package kr.mrjimin.betonquestaddon.compatibility.worldguard

import com.sk89q.worldedit.bukkit.BukkitAdapter
import com.sk89q.worldguard.WorldGuard
import com.sk89q.worldguard.domains.DefaultDomain
import com.sk89q.worldguard.protection.managers.RegionManager
import com.sk89q.worldguard.protection.regions.ProtectedRegion
import org.bukkit.Bukkit
import org.bukkit.Location
import org.bukkit.World
import java.util.*

object WorldGuardUtil {
    private val container get() = WorldGuard.getInstance().platform.regionContainer

    private fun World.getRegionManager(): RegionManager? = container[BukkitAdapter.adapt(this)]

    fun getRegionByName(regionId: String): ProtectedRegion? =
        Bukkit.getWorlds().firstNotNullOfOrNull { it.getRegionManager()?.getRegion(regionId) }

    fun getRegionByName(world: World, regionId: String): ProtectedRegion? =
        world.getRegionManager()?.getRegion(regionId)

    fun getApplicableRegions(location: Location): Set<ProtectedRegion> =
        location.world?.getRegionManager()
            ?.getApplicableRegions(BukkitAdapter.asBlockVector(location))
            ?.regions ?: emptySet()

    fun getRegionByLocation(location: Location): ProtectedRegion? =
        getApplicableRegions(location).maxByOrNull { it.priority }

    fun getRegionIdAt(location: Location): String? =
        getRegionByLocation(location)?.id

    fun isOwner(world: World, regionId: String, uuid: UUID, name: String): Boolean =
        getRegionByName(world, regionId)?.owners?.contains(uuid, name) == true

    fun isMember(world: World, regionId: String, uuid: UUID, name: String): Boolean =
        getRegionByName(world, regionId)?.members?.contains(uuid, name) == true

    fun isOwnerAt(location: Location, uuid: UUID, name: String): Boolean =
        getApplicableRegions(location).any { it.owners.contains(uuid, name) }

    fun isMemberAt(location: Location, uuid: UUID, name: String): Boolean =
        getApplicableRegions(location).any { it.members.contains(uuid, name) }

    private fun DefaultDomain.contains(uuid: UUID, name: String): Boolean =
        this.contains(uuid) || this.contains(name)
}