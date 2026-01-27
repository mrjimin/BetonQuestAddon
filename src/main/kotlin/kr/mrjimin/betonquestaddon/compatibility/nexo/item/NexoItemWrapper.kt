package kr.mrjimin.betonquestaddon.compatibility.nexo.item

import com.nexomc.nexo.api.NexoItems
import net.kyori.adventure.text.Component
import org.betonquest.betonquest.api.QuestException
import org.betonquest.betonquest.api.instruction.Argument
import org.betonquest.betonquest.api.item.QuestItem
import org.betonquest.betonquest.api.profile.Profile
import org.betonquest.betonquest.item.QuestItemWrapper
import org.bukkit.inventory.ItemStack

class NexoItemWrapper(
    private val itemName: Argument<String>
) : QuestItemWrapper {

    override fun getItem(profile: Profile?): QuestItem =
        NexoItem(itemName.getValue(profile))

    class NexoItem(
        private val itemId: String
    ) : QuestItem {

        private val itemBuilder = NexoItems.itemFromId(itemId)
            ?: throw QuestException("Invalid Nexo Item: $itemId")

        override fun getName(): Component =
            itemBuilder.itemName ?: Component.empty()

        override fun getLore(): List<Component> =
            itemBuilder.lore ?: listOf()

        override fun generate(stackSize: Int, profile: Profile?): ItemStack =
            itemBuilder.setAmount(stackSize).build()

        override fun matches(item: ItemStack?): Boolean =
            item != null && itemId == NexoItems.idFromItem(item)
    }
}