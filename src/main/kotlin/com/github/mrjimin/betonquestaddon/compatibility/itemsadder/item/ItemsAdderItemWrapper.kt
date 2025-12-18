package com.github.mrjimin.betonquestaddon.compatibility.itemsadder.item

import dev.lone.itemsadder.api.CustomStack
import net.kyori.adventure.text.Component
import org.betonquest.betonquest.api.instruction.variable.Variable
import org.betonquest.betonquest.api.profile.Profile
import org.betonquest.betonquest.item.QuestItem
import org.betonquest.betonquest.item.QuestItemWrapper
import org.bukkit.inventory.ItemStack

class ItemsAdderItemWrapper(
    private val customStack: Variable<CustomStack>
) : QuestItemWrapper {

    override fun getItem(profile: Profile?): QuestItem =
        ItemsAdderItem(customStack.getValue(profile))

    class ItemsAdderItem(
        private val customStack: CustomStack
    ) : QuestItem {

        override fun getName(): Component =
            customStack.itemStack.itemMeta.itemName()

        override fun getLore(): List<Component>? =
            customStack.itemStack.itemMeta.lore()

        override fun generate(stackSize: Int, profile: Profile?): ItemStack =
            customStack.itemStack.clone().apply {
                amount = stackSize
            }

        override fun matches(item: ItemStack?): Boolean =
            CustomStack.byItemStack(item)?.namespacedID == customStack.namespacedID

    }
}