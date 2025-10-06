package com.github.mrjimin.betonquestaddon.test.listener

import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent

class TestListener : Listener {

    @EventHandler
    fun PlayerJoinEvent.onJoin() {
//        val item = CraftEngineHook.itemFromId("default:palm_sapling") ?: return
//        println(item.id())
//        println(item.material())
//        player.inventory.addItem(item.buildItemStack(5))
//        println("==================")
//        val isItem = CraftEngineHook.exists("default:palm_sapling")
//        println(isItem)
//        val isItem1 = CraftEngineHook.exists("default:palm_saplinga")
//        println(isItem1)
//        println("=============")
//        println(CraftEngineHook.idFromItem(item.buildItemStack()))
//        player.sendMessage(CraftEngineHook.idFromItem(item.buildItemStack())!!.toMiniMessage())
//
//        val loc = Location(Bukkit.getWorld("world"), -16.0, -61.0, -14.0)
//        val head = HeadDatabaseAPI().getItemHead("7129")
//        println(loc)
//
//        val blockDrop = loc.block.drops.firstOrNull() ?: return
//        println(blockDrop)
//        player.inventory.addItem(blockDrop)
//
//        println("================")
//        println(head == blockDrop)

    }
}