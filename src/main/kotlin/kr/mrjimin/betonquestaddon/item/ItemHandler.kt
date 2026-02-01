package kr.mrjimin.betonquestaddon.item

import kr.mrjimin.betonquestaddon.item.factory.CraftEngineItemFactory
import kr.mrjimin.betonquestaddon.item.factory.IAItemFactory
import kr.mrjimin.betonquestaddon.item.factory.NexoItemFactory
import org.betonquest.betonquest.api.QuestException
import org.bukkit.Material
import org.bukkit.inventory.ItemStack

object ItemHandler {

    val ITEM_FACTORIES = hashMapOf(
        "ITEMSADDER" to IAItemFactory,
        "CRAFTENGINE" to CraftEngineItemFactory,
        "NEXO" to NexoItemFactory
    )

    fun createItem(namespace: String): ItemStack {
        return if (namespace.contains(":")) {
            val factoryId = namespace.split(":")[0].uppercase()
            val itemId = namespace.substring(factoryId.length + 1)
            ITEM_FACTORIES[factoryId]?.create(itemId)
                ?: throw QuestException("Invalid $factoryId item ID: $itemId")
        } else {
           ItemStack(Material.valueOf(namespace.uppercase()))
        }
    }
}