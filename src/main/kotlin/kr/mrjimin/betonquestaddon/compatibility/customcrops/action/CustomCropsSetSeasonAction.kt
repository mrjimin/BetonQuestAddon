package kr.mrjimin.betonquestaddon.compatibility.customcrops.action

import net.momirealms.customcrops.api.BukkitCustomCropsPlugin
import net.momirealms.customcrops.api.core.world.Season
import org.betonquest.betonquest.api.instruction.Argument
import org.betonquest.betonquest.api.profile.Profile
import org.betonquest.betonquest.api.quest.action.PlayerAction
import org.bukkit.World

class CustomCropsSetSeasonAction(
    private val world: Argument<World>,
    private val season: Argument<Season>
) : PlayerAction {
    override fun execute(profile: Profile) {
        val cCropsWorld = BukkitCustomCropsPlugin.getInstance().getWorldManager().getWorld(world.getValue(profile))
        cCropsWorld.get().extraData().season = season.getValue(profile)
    }
}