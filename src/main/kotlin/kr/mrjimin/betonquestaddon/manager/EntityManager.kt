package kr.mrjimin.betonquestaddon.manager

import org.bukkit.Bukkit
import org.bukkit.entity.Entity
import org.bukkit.plugin.java.JavaPlugin

object EntityManager {

    private lateinit var plugin: JavaPlugin

    private val entities = mutableMapOf<Entity, Long>()

    fun init(plugin: JavaPlugin) {
        this.plugin = plugin
        start()
    }

    fun register(entity: Entity, lifetime: Long) {
        entities[entity] = Bukkit.getCurrentTick() + lifetime
    }

    fun remove(entity: Entity) {
        entity.remove()
        entities.remove(entity)
    }

    fun clear() {
        entities.keys.forEach(Entity::remove)
        entities.clear()
    }

    private fun start() {
        Bukkit.getScheduler().runTaskTimer(plugin, Runnable {
            val now = Bukkit.getCurrentTick()

            val it = entities.iterator()
            while (it.hasNext()) {
                val (entity, expire) = it.next()

                if (!entity.isValid || now >= expire) {
                    entity.remove()
                    it.remove()
                }
            }
        }, 1L, 1L)
    }
}