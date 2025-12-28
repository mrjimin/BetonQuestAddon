package com.github.mrjimin.betonquestaddon.compatibility.cosmetics

import org.bukkit.inventory.ItemStack

data class CosmeticsWrapper(
    val key: String,
    val permission: String,
    val itemStack: ItemStack
)
