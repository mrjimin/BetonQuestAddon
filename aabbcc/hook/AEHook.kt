package com.github.mrjimin.betonquestaddon.hook

import com.github.mrjimin.betonquestaddon.util.server.NotFoundPlugin
import com.github.mrjimin.betonquestaddon.util.server.checkPlugin
import net.advancedplugins.ae.api.AEAPI
import org.betonquest.betonquest.api.quest.QuestException
import org.bukkit.inventory.ItemStack

object AEHook {

    init {
        if (!"AdvancedEnchantments".checkPlugin()) throw NotFoundPlugin("AdvancedEnchantments")
    }

    fun hasGroup(group: String): Boolean = group in getGroups()

    fun getGroups(): List<String> = AEAPI.getGroups() + "ANY"

    fun applyEnchant(name: String, level: Int, item: ItemStack): ItemStack {
        if (!AEAPI.isAnEnchantment(name)) throw QuestException("Enchant $name is not an Enchantment")
        val level = if (level == -1) returnMaxEnchantLevel(name, level) else level
        return AEAPI.applyEnchant(name, level, item)
    }

    fun returnMaxEnchantLevel(name: String, level: Int): Int {
        val eLevel = AEAPI.getHighestEnchantmentLevel(name)
        return minOf(level, eLevel)
    }

}