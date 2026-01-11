package com.github.mrjimin.betonquestaddon.compatibility.craftengine.objective

import com.github.mrjimin.betonquestaddon.config.NotifyMessage
import net.momirealms.craftengine.bukkit.api.event.FurnitureBreakEvent
import net.momirealms.craftengine.bukkit.api.event.FurnitureInteractEvent
import net.momirealms.craftengine.bukkit.api.event.FurniturePlaceEvent
import net.momirealms.craftengine.bukkit.entity.furniture.BukkitFurniture
import org.betonquest.betonquest.api.CountingObjective
import org.betonquest.betonquest.api.QuestException
import org.betonquest.betonquest.api.instruction.Argument
import org.betonquest.betonquest.api.quest.objective.service.ObjectiveService
import org.bukkit.entity.Player
import org.bukkit.event.Cancellable

class CraftEngineFurnitureObjective(
    service: ObjectiveService,
    targetAmount: Argument<Number>,
    private val identifier: Argument<List<String>>,
    private val isCancelled: Argument<Boolean>,
    notifyMessage: NotifyMessage
) : CountingObjective(service, targetAmount, notifyMessage.toKey()) {

    fun onPlace(event: FurniturePlaceEvent) {
        println(event.furniture())
        println(event.furniture().id().toString())
        handle(event.player, event.furniture(), event)
    }

    fun onBreak(event: FurnitureBreakEvent) {
        println(event.furniture())
        println(event.furniture().id().toString())
        handle(event.player, event.furniture(), event)
    }

    fun onInteract(event: FurnitureInteractEvent) {
        println(event.furniture())
        println(event.furniture().id().toString())
        handle(event.player, event.furniture(), event)
    }

    @Throws(QuestException::class)
    private fun handle(player: Player, bukkitFurniture: BukkitFurniture, event: Cancellable) {
        val profile = service.profileProvider.getProfile(player) ?: return
        if (!service.containsProfile(profile) || !service.checkConditions(profile)) return

        if (identifier.getValue(profile).contains(bukkitFurniture.id().toString())) {
            getCountingData(profile)?.progress()
            completeIfDoneOrNotify(profile)

            if (isCancelled.getValue(profile)) event.isCancelled = true
        }
    }

}