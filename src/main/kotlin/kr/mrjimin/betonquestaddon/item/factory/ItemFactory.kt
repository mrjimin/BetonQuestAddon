package kr.mrjimin.betonquestaddon.item.factory

import org.bukkit.inventory.ItemStack

interface ItemFactory {
    fun create(namespace: String): ItemStack?
}