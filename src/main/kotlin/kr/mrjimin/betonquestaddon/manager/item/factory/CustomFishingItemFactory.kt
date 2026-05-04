package kr.mrjimin.betonquestaddon.manager.item.factory

import net.momirealms.customfishing.api.BukkitCustomFishingPlugin
import net.momirealms.customfishing.api.mechanic.context.Context
import org.bukkit.inventory.ItemStack

object CustomFishingItemFactory : ItemFactory {
    override fun create(namespace: String): ItemStack? {
        return BukkitCustomFishingPlugin.getInstance().itemManager.buildInternal(Context.player(null), namespace)
    }
}