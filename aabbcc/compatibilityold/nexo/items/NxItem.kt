package com.github.mrjimin.betonquestaddon.compatibilityold.nexo.items

import com.nexomc.nexo.api.NexoItems
import com.nexomc.nexo.items.ItemBuilder
import net.kyori.adventure.text.Component
import org.betonquest.betonquest.api.profile.Profile
import org.betonquest.betonquest.item.QuestItem
import org.bukkit.inventory.ItemStack

class NxItem(
    private val itemId: String
) : QuestItem {

    private val itemBuilder: ItemBuilder? = NexoItems.itemFromId(itemId)

    override fun getName(): Component? {
        return itemBuilder?.itemName
    }

    override fun getLore(): List<Component?>? {
        return itemBuilder?.lore?.map { it }
    }

    override fun generate(int: Int, profile: Profile?): ItemStack? {
        val item = NexoItems.itemFromId(itemId) ?: return null
        return item.setAmount(int).build()
    }

    override fun matches(item: ItemStack?): Boolean {
        val matchedId = NexoItems.idFromItem(item)
        return matchedId == itemId
    }
}