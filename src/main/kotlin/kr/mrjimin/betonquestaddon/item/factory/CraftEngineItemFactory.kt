package kr.mrjimin.betonquestaddon.item.factory

import net.momirealms.craftengine.bukkit.api.CraftEngineItems
import net.momirealms.craftengine.core.util.Key
import org.bukkit.inventory.ItemStack

object CraftEngineItemFactory : ItemFactory {
    override fun create(namespace: String): ItemStack? {
        return CraftEngineItems.byId(Key.of(namespace))?.buildItemStack()
    }
}