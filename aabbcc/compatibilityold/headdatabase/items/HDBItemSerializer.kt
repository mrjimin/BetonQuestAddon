package com.github.mrjimin.betonquestaddon.compatibilityold.headdatabase.items

import me.arcaniax.hdb.api.HeadDatabaseAPI
import org.betonquest.betonquest.api.quest.QuestException
import org.betonquest.betonquest.item.QuestItemSerializer
import org.bukkit.inventory.ItemStack

object HDBItemSerializer : QuestItemSerializer {
    override fun serialize(itemStack: ItemStack): String {
        if (!itemStack.hasItemMeta())
            throw QuestException("ItemStack is null or missing metadata.")

        val id = HeadDatabaseAPI().getItemID(itemStack) ?:
            throw QuestException("The item does not contain a valid HeadDatabase ID.")

        return id
    }
}