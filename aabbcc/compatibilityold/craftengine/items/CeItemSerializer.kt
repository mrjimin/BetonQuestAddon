package com.github.mrjimin.betonquestaddon.compatibilityold.craftengine.items

import com.github.mrjimin.betonquestaddon.hook.CraftEngineHook
import org.betonquest.betonquest.api.quest.QuestException
import org.betonquest.betonquest.item.QuestItemSerializer
import org.bukkit.inventory.ItemStack

object CeItemSerializer : QuestItemSerializer {
    override fun serialize(itemStack: ItemStack): String {
        if (!itemStack.hasItemMeta())
            throw QuestException("ItemStack is null or missing metadata.")

        val id = CraftEngineHook.idFromItem(itemStack)
            ?: throw QuestException("The item does not contain a valid CraftEngine ID.")

        return id
    }
}