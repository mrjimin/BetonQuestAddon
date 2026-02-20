package kr.mrjimin.betonquestaddon.compatibility.worldguard.condition

import com.sk89q.worldguard.protection.regions.ProtectedRegion
import kr.mrjimin.betonquestaddon.compatibility.worldguard.WorldGuardUtil
import org.betonquest.betonquest.api.instruction.Argument
import org.betonquest.betonquest.api.profile.Profile
import org.betonquest.betonquest.api.quest.condition.NullableCondition
import org.bukkit.World
import org.bukkit.entity.Player

abstract class AbstractWGCondition(
    private val world: Argument<World>,
    private val region: Argument<String>?
) : NullableCondition {

    protected fun getTargetRegion(profile: Profile?): ProtectedRegion? {
        val targetWorld = world.getValue(profile) ?: return null

        return if (region != null) {
            val id = region.getValue(profile) ?: return null
            WorldGuardUtil.getRegionByName(targetWorld, id)
        } else {
            val player = profile?.player as? Player ?: return null
            WorldGuardUtil.getRegionByLocation(player.location)
        }
    }

    override fun isPrimaryThreadEnforced(): Boolean = true
}