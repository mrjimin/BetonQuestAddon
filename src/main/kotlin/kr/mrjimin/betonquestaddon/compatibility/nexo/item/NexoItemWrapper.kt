package kr.mrjimin.betonquestaddon.compatibility.nexo.item

import com.nexomc.nexo.api.NexoItems
import kr.mrjimin.betonquestaddon.item.ItemHandler
import net.kyori.adventure.text.Component
import org.betonquest.betonquest.api.instruction.Argument
import org.betonquest.betonquest.api.item.QuestItem
import org.betonquest.betonquest.api.profile.Profile
import org.betonquest.betonquest.api.item.QuestItemWrapper
import org.bukkit.inventory.ItemStack

class NexoItemWrapper(
    private val itemName: Argument<String>
) : QuestItemWrapper {

    override fun getItem(profile: Profile?): QuestItem =
        NexoItem(itemName.getValue(profile))

    data class NexoItem(
        private val itemId: String
    ) : QuestItem {

        private val customItem = ItemHandler.createItem("NEXO:$itemId")
        private val customItemMeta = customItem.itemMeta

        override fun getName(): Component =
            customItemMeta.itemName()

        override fun getLore(): List<Component> =
            customItemMeta.lore() ?: listOf()

        override fun generate(stackSize: Int, profile: Profile?): ItemStack =
            customItem.asQuantity(stackSize)

        override fun matches(item: ItemStack?): Boolean =
            item != null && itemId == NexoItems.idFromItem(item)
    }
}