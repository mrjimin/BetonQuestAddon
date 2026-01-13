package com.github.mrjimin.betonquestaddon.compatibility.customnameplates.condition

import com.github.mrjimin.betonquestaddon.compatibility.customnameplates.NpCheckType
import net.momirealms.customnameplates.api.CustomNameplatesAPI
import org.betonquest.betonquest.api.instruction.Argument
import org.betonquest.betonquest.api.profile.OnlineProfile
import org.betonquest.betonquest.api.quest.condition.online.OnlineCondition

class NpEquippedCondition(
    private val type: NpCheckType,
    private val id: Argument<String>
) : OnlineCondition {
    override fun check(profile: OnlineProfile): Boolean {
        val target = id.getValue(profile)
        val api = CustomNameplatesAPI.getInstance().getPlayer(profile.playerUUID) ?: return false

        return target == when (type) {
            NpCheckType.NAMEPLATE -> api.currentNameplate()
            NpCheckType.BUBBLE -> api.currentBubble()
        }
    }
}