package com.github.seojimin0402.betonquestaddon.compatibility.craftengine.item

import net.kyori.adventure.text.Component
import net.momirealms.craftengine.bukkit.api.CraftEngineItems
import net.momirealms.craftengine.core.util.Key
import org.betonquest.betonquest.api.instruction.variable.Variable
import org.betonquest.betonquest.api.profile.Profile
import org.betonquest.betonquest.item.QuestItem
import org.betonquest.betonquest.item.QuestItemWrapper
import org.bukkit.inventory.ItemStack

data class CraftEngineItemWrapper (
    private val itemName: Variable<String>
) : QuestItemWrapper {

    override fun getItem(profile: Profile?): QuestItem? =
        CraftEngineItem(itemName.getValue(profile))

    class CraftEngineItem(
        private val itemId: String
    ) : QuestItem {

        private val customItemMeta = CraftEngineItems.byId(Key.of(itemId))
            ?.buildItemStack()?.itemMeta

        override fun getName(): Component? =
            customItemMeta?.itemName()

        override fun getLore(): List<Component?>? =
            customItemMeta?.lore()

        override fun generate(stackSize: Int, profile: Profile?): ItemStack? =
            CraftEngineItems.byId(Key.of(itemId))?.buildItemStack(stackSize)?.clone()

        override fun matches(item: ItemStack?): Boolean =
            Key.of(itemId) == item?.let(CraftEngineItems::getCustomItemId)

    }
}