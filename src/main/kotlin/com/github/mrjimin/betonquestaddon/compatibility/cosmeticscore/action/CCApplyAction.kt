package com.github.mrjimin.betonquestaddon.compatibility.cosmeticscore.action

import com.github.mrjimin.betonquestaddon.compatibility.cosmeticscore.CosmeticsCoreProvider
import org.betonquest.betonquest.api.QuestException
import org.betonquest.betonquest.api.instruction.Argument
import org.betonquest.betonquest.api.profile.Profile
import org.betonquest.betonquest.api.quest.action.PlayerAction
import org.bukkit.entity.Player

class CCApplyAction(
    private val cosmetics: Argument<String>,
    private val ignore: Argument<Boolean>
) : PlayerAction {
    override fun execute(profile: Profile) {
        val id = cosmetics.getValue(profile)

        id.takeIf { CosmeticsCoreProvider.get(id) != null }
            ?: throw QuestException("CosmeticsCore not found for ID: $id")

        val player = profile.player as Player
        if (!ignore.getValue(profile) && !CosmeticsCoreProvider.owns(player, id)) {
            throw QuestException("Player does not have permission for cosmetic: $id")
        }

        CosmeticsCoreProvider.equip(player, id)
    }
}