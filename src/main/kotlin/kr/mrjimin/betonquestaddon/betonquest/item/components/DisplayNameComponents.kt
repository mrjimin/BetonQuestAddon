package kr.mrjimin.betonquestaddon.betonquest.item.components

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.TextDecoration
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.ItemMeta

class DisplayNameComponents(
    displayName: Component
) : ItemComponents {

    val displayName = displayName.decoration(TextDecoration.ITALIC, TextDecoration.State.FALSE)
    override fun apply(itemMeta: ItemMeta) {
        itemMeta.displayName(displayName)
    }

//    override fun check(itemStack: ItemStack): Boolean {
//        val currentName = itemStack.itemMeta?.displayName() ?: return false
//        return currentName.compact() == displayName
//    }

    override fun key(): Set<String> = setOf("display-name")
}