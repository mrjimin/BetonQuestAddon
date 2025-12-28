package com.github.mrjimin.betonquestaddon.compatibility.itemsadder.item

import dev.lone.itemsadder.api.CustomStack
import org.betonquest.betonquest.api.QuestException
import org.betonquest.betonquest.item.QuestItemSerializer
import org.bukkit.inventory.ItemStack

class ItemsAdderQuestItemSerializer : QuestItemSerializer {
    override fun serialize(itemStack: ItemStack?): String? {
        val customStack = CustomStack.byItemStack(itemStack)
            ?: throw QuestException("Item is not a ItemsAdder Item!")

        return customStack.namespacedID
    }
}