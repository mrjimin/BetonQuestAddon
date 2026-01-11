package com.github.mrjimin.betonquestaddon.test

import net.momirealms.craftengine.bukkit.api.event.FurnitureBreakEvent
import net.momirealms.craftengine.bukkit.api.event.FurnitureInteractEvent
import net.momirealms.craftengine.bukkit.api.event.FurniturePlaceEvent
import org.bukkit.event.EventHandler
import org.bukkit.event.EventPriority
import org.bukkit.event.Listener

class TestListener : Listener {

    @EventHandler(ignoreCancelled = true)
    fun onPlace(event: FurniturePlaceEvent) {
        println(event.furniture())
        println(event.furniture().id().toString())
    }

    @EventHandler(ignoreCancelled = true)
    fun onBreak(event: FurnitureBreakEvent) {
        println(event.furniture())
        println(event.furniture().id().toString())
    }

    @EventHandler(ignoreCancelled = true)
    fun onInteract(event: FurnitureInteractEvent) {
        println(event.furniture())
        println(event.furniture().id().toString())
    }
}