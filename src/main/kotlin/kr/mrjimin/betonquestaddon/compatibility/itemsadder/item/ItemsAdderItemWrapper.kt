package kr.mrjimin.betonquestaddon.compatibility.itemsadder.item

import dev.lone.itemsadder.api.CustomStack
import kr.mrjimin.betonquestaddon.item.ItemHandler
import net.kyori.adventure.text.Component
import org.betonquest.betonquest.api.instruction.Argument
import org.betonquest.betonquest.api.item.QuestItem
import org.betonquest.betonquest.api.profile.Profile
import org.betonquest.betonquest.api.item.QuestItemWrapper
import org.bukkit.inventory.ItemStack

class ItemsAdderItemWrapper(
    private val itemId: Argument<String>
) : QuestItemWrapper {

    override fun getItem(profile: Profile?): QuestItem =
        ItemsAdderItem(itemId.getValue(profile))

    data class ItemsAdderItem(
        private val itemId: String
    ) : QuestItem {

        private val customItem = ItemHandler.createItem("ITEMSADDER:$itemId")
        private val customItemMeta = customItem.itemMeta

        override fun getName(): Component =
            customItemMeta.itemName()

        override fun getLore(): List<Component> =
            customItemMeta.lore() ?: listOf()

        override fun generate(stackSize: Int, profile: Profile?): ItemStack =
            customItem.asQuantity(stackSize)

        override fun matches(item: ItemStack?): Boolean =
            item != null && itemId == CustomStack.byItemStack(item)?.namespacedID

    }
}