package com.github.mrjimin.betonquestaddon.compatibility.customfishing.item

import net.kyori.adventure.text.Component
import net.momirealms.customfishing.api.mechanic.context.Context
import net.momirealms.customfishing.api.mechanic.item.ItemManager
import org.betonquest.betonquest.api.QuestException
import org.betonquest.betonquest.api.instruction.Argument
import org.betonquest.betonquest.api.item.QuestItem
import org.betonquest.betonquest.api.profile.Profile
import org.betonquest.betonquest.item.QuestItemWrapper
import org.bukkit.inventory.ItemStack

class CFishingItemWrapper(
    private val itemId: Argument<String>,
    private val itemManager: ItemManager
) : QuestItemWrapper {

    override fun getItem(profile: Profile?): QuestItem =
        CFishingItem(itemId.getValue(profile), itemManager)

    class CFishingItem(
        private val itemId: String,
        private val itemManager: ItemManager
    ) : QuestItem {

        private val cFishingItem = itemManager.buildInternal(Context.player(null), itemId)
            ?: throw QuestException("Invalid CustomFishing Item: $itemId")

        override fun getName(): Component =
            cFishingItem.displayName()

        override fun getLore(): List<Component> =
            cFishingItem.lore() ?: listOf()

        override fun generate(stackSize: Int, profile: Profile?): ItemStack =
            cFishingItem.asQuantity(stackSize)

        override fun matches(item: ItemStack?): Boolean =
            item != null && itemId == itemManager.getCustomFishingItemID(item)

    }
}