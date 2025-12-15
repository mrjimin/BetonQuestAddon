package com.github.seojimin0402.betonquestaddon.compatibility.itemsadder.item

import dev.lone.itemsadder.api.CustomStack
import net.kyori.adventure.text.Component
import org.betonquest.betonquest.api.instruction.variable.Variable
import org.betonquest.betonquest.api.profile.Profile
import org.betonquest.betonquest.item.QuestItem
import org.betonquest.betonquest.item.QuestItemWrapper
import org.bukkit.inventory.ItemStack

data class ItemsAdderItemWrapper(
    private val stack: Variable<CustomStack>
) : QuestItemWrapper {

    override fun getItem(profile: Profile?): QuestItem? =
        ItemsAdderItem(stack.getValue(profile))

    class ItemsAdderItem(
        private val stack: CustomStack
    ) : QuestItem {

        override fun getName(): Component? =
            stack.itemStack.itemMeta.itemName()

        override fun getLore(): List<Component?>? =
            stack.itemStack.itemMeta.lore()

        override fun generate(stackSize: Int, profile: Profile?): ItemStack? =
            stack.itemStack.clone().apply {
                amount = stackSize
            }

        override fun matches(item: ItemStack?): Boolean =
            CustomStack.byItemStack(item)?.namespacedID == stack.namespacedID

    }
}