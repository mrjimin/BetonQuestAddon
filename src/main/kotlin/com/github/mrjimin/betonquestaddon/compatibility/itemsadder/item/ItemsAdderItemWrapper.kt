package com.github.mrjimin.betonquestaddon.compatibility.itemsadder.item

import dev.lone.itemsadder.api.CustomStack
import net.kyori.adventure.text.Component
import org.betonquest.betonquest.api.QuestException
import org.betonquest.betonquest.api.instruction.Argument
import org.betonquest.betonquest.api.item.QuestItem
import org.betonquest.betonquest.api.profile.Profile
import org.betonquest.betonquest.item.QuestItemWrapper
import org.bukkit.inventory.ItemStack

class ItemsAdderItemWrapper(
    private val itemId: Argument<String>
) : QuestItemWrapper {

    override fun getItem(profile: Profile?): QuestItem =
        ItemsAdderItem(itemId.getValue(profile))

    class ItemsAdderItem(
        itemId: String
    ) : QuestItem {

        private val customStack = CustomStack.getInstance(itemId)
            ?: throw QuestException("Invalid ItemsAdder Item: $itemId")

        override fun getName(): Component =
            customStack.itemName()

        override fun getLore(): List<Component> =
            customStack.itemStack.lore() ?: listOf()

        override fun generate(stackSize: Int, profile: Profile?): ItemStack =
            customStack.itemStack.asQuantity(stackSize)

        override fun matches(item: ItemStack?): Boolean =
            item != null && CustomStack.byItemStack(item)?.namespacedID == customStack.namespacedID

    }
}