package kr.mrjimin.betonquestaddon.compatibility.worldguard.condition

import kr.mrjimin.betonquestaddon.compatibility.worldguard.TargetType
import org.betonquest.betonquest.api.instruction.Argument
import org.betonquest.betonquest.api.profile.Profile
import org.bukkit.World

class WGHasCondition(
    private val targetType: TargetType,
    world: Argument<World>,
    region: Argument<String>?
) : AbstractWGCondition(world, region) {

    override fun check(profile: Profile?): Boolean {
        val targetRegion = getTargetRegion(profile) ?: return false

        return when (targetType) {
            TargetType.OWNER -> (targetRegion.owners?.size() ?: 0) > 0
            TargetType.MEMBER -> (targetRegion.members?.size() ?: 0) > 0
        }
    }

    override fun isPrimaryThreadEnforced(): Boolean = true
}