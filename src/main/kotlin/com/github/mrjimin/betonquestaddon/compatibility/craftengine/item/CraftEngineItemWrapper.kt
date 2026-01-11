package com.github.mrjimin.betonquestaddon.compatibility.craftengine.item

import com.github.mrjimin.betonquestaddon.compatibility.craftengine.asCraftKey
import net.kyori.adventure.text.Component
import net.momirealms.craftengine.bukkit.api.CraftEngineItems
import org.betonquest.betonquest.api.QuestException
import org.betonquest.betonquest.api.instruction.Argument
import org.betonquest.betonquest.api.item.QuestItem
import org.betonquest.betonquest.api.profile.Profile
import org.betonquest.betonquest.item.QuestItemWrapper
import org.bukkit.inventory.ItemStack

class CraftEngineItemWrapper (
    private val itemId: Argument<String>
) : QuestItemWrapper {

    override fun getItem(profile: Profile?): QuestItem =
        CraftEngineItem(itemId.getValue(profile))

    class CraftEngineItem(
        private val itemId: String
    ) : QuestItem {

        private val customItem = CraftEngineItems.byId(itemId.asCraftKey())
            ?: throw QuestException("Invalid CraftEngine Item: $itemId")

        private val buildItemMeta = customItem.buildItemStack().itemMeta

        override fun getName(): Component =
            buildItemMeta.itemName()

        override fun getLore(): List<Component> =
            buildItemMeta.lore() ?: listOf()

        override fun generate(stackSize: Int, profile: Profile?): ItemStack =
            customItem.buildItemStack(stackSize)

        override fun matches(item: ItemStack?): Boolean =
            item != null && itemId.asCraftKey() == CraftEngineItems.getCustomItemId(item)

    }
}