package com.github.mrjimin.betonquestaddon.compatibilityold.headdatabase.items

import me.arcaniax.hdb.api.HeadDatabaseAPI
import net.kyori.adventure.text.Component
import org.betonquest.betonquest.api.profile.Profile
import org.betonquest.betonquest.item.QuestItem
import org.bukkit.inventory.ItemStack

class HDBItem(
    private val itemId: String
) : QuestItem {

    private val hdbItemMeta = HeadDatabaseAPI().getItemHead(itemId).itemMeta

    override fun getName(): Component? {
        return hdbItemMeta?.itemName()
    }

    override fun getLore(): List<Component?>? {
        return hdbItemMeta?.lore()?.map { it }
    }

    override fun generate(int: Int, profile: Profile?): ItemStack? {
        val item = HeadDatabaseAPI().getItemHead(itemId).clone()
        item.amount = int
        return item
    }

    override fun matches(item: ItemStack?): Boolean {
        val id = HeadDatabaseAPI().getItemID(item)
        return id == itemId
    }
}