package com.github.mrjimin.betonquestaddon.compatibility.cosmeticscore.condition

import com.github.mrjimin.betonquestaddon.compatibility.cosmeticscore.CosmeticsCoreProvider
import org.betonquest.betonquest.api.profile.Profile
import org.betonquest.betonquest.api.quest.condition.PlayerCondition
import org.bukkit.entity.Player

class CCInWardrobeCondition : PlayerCondition {
    override fun check(profile: Profile): Boolean {
        return CosmeticsCoreProvider.isInWardrobe(profile.player as Player)
    }
}