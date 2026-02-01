package kr.mrjimin.betonquestaddon.compatibility.craftengine.item

import kr.mrjimin.betonquestaddon.item.ItemHandler
import net.kyori.adventure.text.Component
import net.momirealms.craftengine.bukkit.api.CraftEngineItems
import net.momirealms.craftengine.core.util.Key
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

    data class CraftEngineItem(
        private val itemId: String
    ) : QuestItem {

        private val customItem = ItemHandler.createItem("CRAFTENGINE:$itemId")
        private val customItemMeta = customItem.itemMeta

        override fun getName(): Component =
            customItemMeta.itemName()

        override fun getLore(): List<Component> =
            customItemMeta.lore() ?: listOf()

        override fun generate(stackSize: Int, profile: Profile?): ItemStack =
            customItem.asQuantity(stackSize)

        override fun matches(item: ItemStack?): Boolean =
            item != null && Key.of(itemId) == CraftEngineItems.getCustomItemId(item)

    }
}