package kr.mrjimin.betonquestaddon.compatibility.hmccosmetics.condition

import kr.mrjimin.betonquestaddon.compatibility.hmccosmetics.HMCCosmeticsProvider
import org.betonquest.betonquest.api.profile.Profile
import org.betonquest.betonquest.api.quest.condition.PlayerCondition
import org.bukkit.entity.Player

class HMCInWardrobeCondition : PlayerCondition {
    override fun check(profile: Profile): Boolean {
        val player = profile.player as Player
        return HMCCosmeticsProvider.isInWardrobe(player)
    }
}