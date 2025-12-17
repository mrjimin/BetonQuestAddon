package com.github.mrjimin.betonquestaddon.compatibility.nexo.item

import com.nexomc.nexo.api.NexoItems
import com.nexomc.nexo.items.ItemBuilder
import net.kyori.adventure.text.Component
import org.betonquest.betonquest.api.instruction.variable.Variable
import org.betonquest.betonquest.api.profile.Profile
import org.betonquest.betonquest.api.quest.QuestException
import org.betonquest.betonquest.item.QuestItem
import org.betonquest.betonquest.item.QuestItemWrapper
import org.bukkit.inventory.ItemStack

class NexoItemWrapper(
    private val itemName: Variable<String>
) : QuestItemWrapper {

    override fun getItem(profile: Profile?): QuestItem =
        NexoItem(itemName.getValue(profile))

    class NexoItem(
        private val itemId: String
    ) : QuestItem {

        private val itemBuilder: ItemBuilder =
            NexoItems.itemFromId(itemId)
                ?: throw QuestException("Invalid Nexo Item: $itemId")

        override fun getName(): Component? =
            itemBuilder.itemName

        override fun getLore(): List<Component>? =
            itemBuilder.lore

        override fun generate(stackSize: Int, profile: Profile?): ItemStack? =
            itemBuilder.setAmount(stackSize)?.build()?.clone()

        override fun matches(item: ItemStack?): Boolean =
            itemId == NexoItems.idFromItem(item)
    }
}