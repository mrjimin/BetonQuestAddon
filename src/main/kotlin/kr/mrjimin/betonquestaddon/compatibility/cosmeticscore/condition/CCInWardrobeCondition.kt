package kr.mrjimin.betonquestaddon.compatibility.cosmeticscore.condition

import kr.mrjimin.betonquestaddon.compatibility.cosmeticscore.CosmeticsCoreProvider
import org.betonquest.betonquest.api.profile.Profile
import org.betonquest.betonquest.api.quest.condition.PlayerCondition
import org.bukkit.entity.Player

class CCInWardrobeCondition : PlayerCondition {
    override fun check(profile: Profile): Boolean {
        val player = profile.player as Player
        return CosmeticsCoreProvider.isInWardrobe(player)
    }
}