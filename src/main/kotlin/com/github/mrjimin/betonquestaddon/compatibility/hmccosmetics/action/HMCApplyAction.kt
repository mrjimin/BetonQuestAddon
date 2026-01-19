package com.github.mrjimin.betonquestaddon.compatibility.hmccosmetics.action

import com.github.mrjimin.betonquestaddon.compatibility.hmccosmetics.HMCCosmeticsProvider
import org.betonquest.betonquest.api.QuestException
import org.betonquest.betonquest.api.instruction.Argument
import org.betonquest.betonquest.api.profile.Profile
import org.betonquest.betonquest.api.quest.action.PlayerAction
import org.bukkit.entity.Player

class HMCApplyAction(
    private val cosmetics: Argument<String>,
    private val ignore: Argument<Boolean>
) : PlayerAction {
    override fun execute(profile: Profile) {
        val id = cosmetics.getValue(profile)

        id.takeIf { HMCCosmeticsProvider.get(id) != null }
            ?: throw QuestException("HMCCosmetic not found for ID: $id")

        val player = profile.player as Player
        if (!ignore.getValue(profile) && !HMCCosmeticsProvider.owns(player, id)) {
            throw QuestException("Player does not have permission for cosmetic: $id")
        }

        HMCCosmeticsProvider.equip(player, id)
    }
}