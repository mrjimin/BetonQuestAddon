package com.github.mrjimin.betonquestaddon.compatibility.craftengine.item

import net.momirealms.craftengine.bukkit.api.CraftEngineItems
import org.betonquest.betonquest.api.QuestException
import org.betonquest.betonquest.item.QuestItemSerializer
import org.bukkit.inventory.ItemStack

class CraftEngineQuestItemSerializer : QuestItemSerializer {

    override fun serialize(itemStack: ItemStack): String {
        return CraftEngineItems.getCustomItemId(itemStack)?.toString()
            ?: throw QuestException("Item is not a CraftEngine Item!")
    }
}