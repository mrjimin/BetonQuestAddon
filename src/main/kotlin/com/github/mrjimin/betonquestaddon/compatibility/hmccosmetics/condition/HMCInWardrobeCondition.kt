package com.github.mrjimin.betonquestaddon.compatibility.hmccosmetics.condition

import com.github.mrjimin.betonquestaddon.compatibility.hmccosmetics.HMCCosmeticsProvider
import org.betonquest.betonquest.api.profile.Profile
import org.betonquest.betonquest.api.quest.condition.PlayerCondition
import org.bukkit.entity.Player

class HMCInWardrobeCondition : PlayerCondition {
    override fun check(profile: Profile): Boolean {
        return HMCCosmeticsProvider.isInWardrobe(profile.player as Player)
    }
}