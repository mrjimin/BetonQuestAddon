package kr.mrjimin.betonquestaddon.compatibility.itemsadder.item

import dev.lone.itemsadder.api.CustomStack
import org.betonquest.betonquest.api.QuestException
import org.betonquest.betonquest.item.QuestItemSerializer
import org.bukkit.inventory.ItemStack

class ItemsAdderQuestItemSerializer : QuestItemSerializer {
    override fun serialize(itemStack: ItemStack): String {
        return CustomStack.byItemStack(itemStack)?.namespacedID
            ?: throw QuestException("Item is not a ItemsAdder Item!")
    }
}