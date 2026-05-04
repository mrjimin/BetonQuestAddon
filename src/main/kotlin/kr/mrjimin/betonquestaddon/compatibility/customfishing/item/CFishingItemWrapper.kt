package kr.mrjimin.betonquestaddon.compatibility.customfishing.item

import kr.mrjimin.betonquestaddon.manager.item.ItemHandler
import net.kyori.adventure.text.Component
import net.momirealms.customfishing.api.mechanic.item.ItemManager
import org.betonquest.betonquest.api.instruction.Argument
import org.betonquest.betonquest.api.item.QuestItem
import org.betonquest.betonquest.api.item.QuestItemWrapper
import org.betonquest.betonquest.api.profile.Profile
import org.bukkit.inventory.ItemStack

class CFishingItemWrapper(
    private val itemId: Argument<String>,
    private val itemManager: ItemManager,
) : QuestItemWrapper {

    override fun getItem(profile: Profile?): QuestItem =
        CFishingItem(itemId.getValue(profile), itemManager)

    class CFishingItem(
        private val itemId: String,
        private val itemManager: ItemManager,
    ) : QuestItem {

        private val customItem = ItemHandler.createItem("CUSTOMFISHING:$itemId")

        override fun getName(): Component =
            customItem.displayName()

        override fun getLore(): List<Component> =
            customItem.lore() ?: listOf()

        override fun generate(stackSize: Int, profile: Profile?): ItemStack =
            customItem.asQuantity(stackSize)

        override fun matches(item: ItemStack?): Boolean =
            item != null && itemId == itemManager.getCustomFishingItemID(item)

    }
}