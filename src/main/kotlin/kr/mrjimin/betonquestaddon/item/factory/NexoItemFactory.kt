package kr.mrjimin.betonquestaddon.item.factory

import com.nexomc.nexo.api.NexoItems
import org.bukkit.inventory.ItemStack

object NexoItemFactory : ItemFactory {
    override fun create(namespace: String): ItemStack? {
        return NexoItems.itemFromId(namespace)?.build()
    }
}