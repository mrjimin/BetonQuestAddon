package com.github.mrjimin.betonquestaddon.compatibility.advancedenchantments.events

import com.github.mrjimin.betonquestaddon.hook.AEHook
import org.betonquest.betonquest.api.profile.Profile
import org.betonquest.betonquest.api.quest.event.PlayerEvent
import org.betonquest.betonquest.api.instruction.variable.Variable

class AEEnchantment(
    private val name: Variable<String>,
    private val level: Variable<Number>
) : PlayerEvent {

    override fun execute(profile: Profile) {
        val player = profile.player.takeIf { it.isOnline }?.player ?: return

        val mainHandItem = player.inventory.itemInMainHand
        if (mainHandItem.isEmpty) return

        val enchantName = name.getValue(profile)
        val enchantLevel = level.getValue(profile).toInt()

        val enchantedItem = AEHook.applyEnchant(enchantName, enchantLevel, mainHandItem)
        player.inventory.setItemInMainHand(enchantedItem)
    }
}