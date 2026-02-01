package kr.mrjimin.betonquestaddon.betonquest.item.components

import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.ItemMeta

interface ItemComponents {
    fun apply(itemStack: ItemStack) {}
    fun apply(itemMeta: ItemMeta) {}

//    fun check(itemStack: ItemStack): Boolean {}
//    fun check(itemMeta: ItemMeta): Boolean {}

    fun key(): Set<String>
}