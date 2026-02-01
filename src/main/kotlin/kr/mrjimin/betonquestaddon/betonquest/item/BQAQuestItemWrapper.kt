package kr.mrjimin.betonquestaddon.betonquest.item

import dev.lone.itemsadder.api.CustomStack
import net.kyori.adventure.text.Component
import org.betonquest.betonquest.api.item.QuestItem
import org.betonquest.betonquest.api.profile.Profile
import org.betonquest.betonquest.item.QuestItemWrapper
import org.bukkit.inventory.ItemStack

class BQAQuestItemWrapper(
    private val questItem: QuestItem
) : QuestItemWrapper {
    override fun getItem(profile: Profile?): QuestItem = questItem

    class BQAQuestItem(
        private val itemStack: ItemStack
    ) : QuestItem {

        private val itemMeta = itemStack.itemMeta

        override fun getName(): Component =
            itemStack.displayName()

        override fun getLore(): List<Component> =
            itemStack.lore() ?: listOf()

        override fun generate(stackSize: Int, profile: Profile?): ItemStack =
            itemStack.asQuantity(stackSize)

        override fun matches(item: ItemStack?): Boolean {
            val itemMeta = item?.itemMeta ?: return false
            return true
        }


    }

}