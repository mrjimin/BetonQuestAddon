package com.github.mrjimin.betonquestaddon.compatibility.nexo.item

import com.nexomc.nexo.api.NexoItems
import org.betonquest.betonquest.api.quest.QuestException
import org.betonquest.betonquest.item.QuestItemSerializer
import org.bukkit.inventory.ItemStack

class NexoQuestItemSerializer : QuestItemSerializer {

    override fun serialize(itemStack: ItemStack): String {
        return NexoItems.idFromItem(itemStack)
            ?: throw QuestException("Item is not a Nexo Item!")
    }

}