package com.github.mrjimin.betonquestaddon.compatibility.customnameplates.condition

import com.github.mrjimin.betonquestaddon.compatibility.customnameplates.NpCheckType
import net.momirealms.customnameplates.api.CustomNameplatesAPI
import org.betonquest.betonquest.api.instruction.Argument
import org.betonquest.betonquest.api.profile.OnlineProfile
import org.betonquest.betonquest.api.quest.condition.online.OnlineCondition

class NpHasCondition(
    private val type: NpCheckType,
    private val id: Argument<String>,
    private val ignore: Argument<Boolean>
) : OnlineCondition {
    override fun check(profile: OnlineProfile): Boolean {
        val target = id.getValue(profile)
        val shouldIgnore = ignore.getValue(profile)

        if (!shouldIgnore) {
            val exists = when (type) {
                NpCheckType.NAMEPLATE -> CustomNameplatesAPI.getInstance().getNameplate(target).isPresent
                NpCheckType.BUBBLE -> CustomNameplatesAPI.getInstance().getBubble(target).isPresent
            }

            if (!exists) return false
        }

        return when (type) {
            NpCheckType.NAMEPLATE -> profile.player.hasPermission("nameplates.equip.$target")
            NpCheckType.BUBBLE -> profile.player.hasPermission("bubbles.equip.$target")
        }
    }
}