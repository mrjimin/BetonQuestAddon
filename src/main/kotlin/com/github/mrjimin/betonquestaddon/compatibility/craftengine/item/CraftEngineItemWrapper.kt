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
    private val itemName: Argument<String>
) : QuestItemWrapper {

    override fun getItem(profile: Profile?): QuestItem =
        CraftEngineItem(itemName.getValue(profile))

    class CraftEngineItem(
        private val itemId: String
    ) : QuestItem {

        private val customItemMeta = CraftEngineItems.byId(itemId.asCraftKey())
            ?.buildItemStack()?.itemMeta ?: throw QuestException("Invalid CraftEngine Item: $itemId")

        override fun getName(): Component =
            customItemMeta.itemName()

        override fun getLore(): List<Component>? =
            customItemMeta.lore()

        override fun generate(stackSize: Int, profile: Profile?): ItemStack? =
            CraftEngineItems.byId(itemId.asCraftKey())?.buildItemStack(stackSize)?.clone()

        override fun matches(item: ItemStack?): Boolean =
            itemId.asCraftKey() == item?.let(CraftEngineItems::getCustomItemId)

    }
}