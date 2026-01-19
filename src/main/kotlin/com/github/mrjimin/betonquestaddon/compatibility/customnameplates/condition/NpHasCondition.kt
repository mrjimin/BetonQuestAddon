package com.github.mrjimin.betonquestaddon.compatibility.customnameplates.condition

import com.github.mrjimin.betonquestaddon.compatibility.customnameplates.NpCheckType
import net.momirealms.craftengine.core.entity.player.Player
import net.momirealms.customnameplates.api.CustomNameplatesAPI
import org.betonquest.betonquest.api.instruction.Argument
import org.betonquest.betonquest.api.profile.Profile
import org.betonquest.betonquest.api.quest.condition.PlayerCondition

class NpHasCondition(
    private val type: NpCheckType,
    private val id: Argument<String>,
    private val ignore: Argument<Boolean>
) : PlayerCondition {
    override fun check(profile: Profile): Boolean {
        val target = id.getValue(profile)
        val shouldIgnore = ignore.getValue(profile)

        if (!shouldIgnore) {
            val exists = when (type) {
                NpCheckType.NAMEPLATE -> CustomNameplatesAPI.getInstance().getNameplate(target).isPresent
                NpCheckType.BUBBLE -> CustomNameplatesAPI.getInstance().getBubble(target).isPresent
            }

            if (!exists) return false
        }

        val player = profile.player as Player

        return when (type) {
            NpCheckType.NAMEPLATE -> player.hasPermission("nameplates.equip.$target")
            NpCheckType.BUBBLE -> player.hasPermission("bubbles.equip.$target")
        }
    }
}