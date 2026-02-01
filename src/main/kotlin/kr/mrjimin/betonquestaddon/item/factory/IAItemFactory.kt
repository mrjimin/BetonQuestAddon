package kr.mrjimin.betonquestaddon.item.factory

import dev.lone.itemsadder.api.CustomStack
import org.bukkit.inventory.ItemStack

object IAItemFactory : ItemFactory {
    override fun create(namespace: String): ItemStack? {
        return CustomStack.getInstance(namespace)!!.itemStack
    }
}