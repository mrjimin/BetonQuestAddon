package com.github.mrjimin.betonquestaddon.compatibilityold.itemsadder.items

import dev.lone.itemsadder.api.CustomStack
import org.betonquest.betonquest.api.quest.QuestException
import org.betonquest.betonquest.item.QuestItemSerializer
import org.bukkit.inventory.ItemStack

object IaItemSerializer : QuestItemSerializer {
    override fun serialize(itemStack: ItemStack): String {
        if (!itemStack.hasItemMeta())
            throw QuestException("The item is not a ItemsAdder Item!")

        val customStack = CustomStack.byItemStack(itemStack) ?:
            throw QuestException("The item does not contain a custom ItemStack.")

        return customStack.namespacedID
    }
}