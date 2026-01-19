package com.github.mrjimin.betonquestaddon.compatibility.customnameplates.condition

import com.github.mrjimin.betonquestaddon.compatibility.customnameplates.NpCheckType
import net.momirealms.customnameplates.api.CustomNameplatesAPI
import org.betonquest.betonquest.api.instruction.Argument
import org.betonquest.betonquest.api.profile.Profile
import org.betonquest.betonquest.api.quest.condition.PlayerCondition

class NpEquippedCondition(
    private val type: NpCheckType,
    private val id: Argument<String>
) : PlayerCondition {

    override fun check(profile: Profile): Boolean {
        val target = id.getValue(profile)
        val api = CustomNameplatesAPI.getInstance().getPlayer(profile.playerUUID) ?: return false

        return target == when (type) {
            NpCheckType.NAMEPLATE -> api.currentNameplate()
            NpCheckType.BUBBLE -> api.currentBubble()
        }
    }
}