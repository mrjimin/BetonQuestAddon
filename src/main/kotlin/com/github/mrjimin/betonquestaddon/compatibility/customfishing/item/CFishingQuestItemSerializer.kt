package com.github.mrjimin.betonquestaddon.compatibility.customfishing.item

import net.momirealms.customfishing.api.mechanic.item.ItemManager
import org.betonquest.betonquest.api.QuestException
import org.betonquest.betonquest.item.QuestItemSerializer
import org.bukkit.inventory.ItemStack

class CFishingQuestItemSerializer(
    private val itemManager: ItemManager
) : QuestItemSerializer {
    override fun serialize(itemStack: ItemStack): String {
        return itemManager.getCustomFishingItemID(itemStack)
            ?: throw QuestException("Item is not a CustomFishing Item!")
    }

}