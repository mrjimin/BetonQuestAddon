package com.github.mrjimin.betonquestaddon.compatibilityold.nexo.items

import com.nexomc.nexo.api.NexoItems
import org.betonquest.betonquest.api.quest.QuestException
import org.betonquest.betonquest.item.QuestItemSerializer
import org.bukkit.inventory.ItemStack

object NxItemSerializer : QuestItemSerializer {
    override fun serialize(itemStack: ItemStack): String {
        if (!itemStack.hasItemMeta())
            throw QuestException("ItemStack is null or missing metadata.")

        val id = NexoItems.idFromItem(itemStack) ?:
            throw QuestException("The item does not contain a valid Nexo ID.")

        return id
    }
}