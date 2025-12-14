package com.github.mrjimin.betonquestaddon.hook

 import me.arcaniax.hdb.api.HeadDatabaseAPI
import org.bukkit.Location

object HDBHook {

    fun isHDBHead(location: Location): Boolean {
        val block = location.block
        val itemStack = block.drops.firstOrNull() ?: return false
        return HeadDatabaseAPI().getItemID(itemStack) != null
    }

    fun getHDBId(location: Location): String? {
        val block = location.block
        val itemStack = block.drops.firstOrNull() ?: return null
        return HeadDatabaseAPI().getItemID(itemStack) ?: return null
    }

}