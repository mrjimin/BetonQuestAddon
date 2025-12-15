package com.github.seojimin0402.betonquestaddon.test.listener.lands

import me.angeschossen.lands.api.events.LandCreateEvent
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener

class LandsListener : Listener {

    @EventHandler
    fun LandCreateEvent.onChunkPreClaim() {
        val lp = landPlayer
        val l = land
        println(lp)
        println(lp?.player)
        println(lp?.name)
        println("=======")
        println(l.name)
        println(l.spawn)
        println(l.onlinePlayers)
    }
}