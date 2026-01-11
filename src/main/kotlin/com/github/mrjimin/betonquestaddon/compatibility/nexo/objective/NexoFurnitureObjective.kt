package com.github.mrjimin.betonquestaddon.compatibility.nexo.objective

import com.github.mrjimin.betonquestaddon.config.NotifyMessage
import com.github.mrjimin.betonquestaddon.util.action.Action
import com.nexomc.nexo.api.NexoFurniture
import com.nexomc.nexo.api.events.furniture.NexoFurnitureBreakEvent
import com.nexomc.nexo.api.events.furniture.NexoFurnitureInteractEvent
import com.nexomc.nexo.api.events.furniture.NexoFurniturePlaceEvent
import org.betonquest.betonquest.api.CountingObjective
import org.betonquest.betonquest.api.QuestException
import org.betonquest.betonquest.api.instruction.Argument
import org.betonquest.betonquest.api.quest.objective.service.ObjectiveService
import org.bukkit.entity.Entity
import org.bukkit.entity.Player

class NexoFurnitureObjective(
    service: ObjectiveService,
    targetAmount: Argument<Number>,
    // private val identifier: Argument<String>,
    private val identifier: Argument<List<String>>,
    notifyMessage: NotifyMessage,
) : CountingObjective(service, targetAmount, notifyMessage.toKey()) {

    fun onPlace(event: NexoFurniturePlaceEvent) {
        handle(event.player, event.baseEntity)
    }

    fun onBreak(event: NexoFurnitureBreakEvent) {
        handle(event.player, event.baseEntity)
    }

    fun onInteract(event: NexoFurnitureInteractEvent) {
        handle(event.player, event.baseEntity)
    }

    @Throws(QuestException::class)
    private fun handle(player: Player, entity: Entity) {
        val profile = service.profileProvider.getProfile(player) ?: return
        if (!service.containsProfile(profile) || !service.checkConditions(profile)) return

        // if (identifier.getValue(profile) == NexoFurniture.furnitureMechanic(entity)?.itemID) {
        if (identifier.getValue(profile).contains(NexoFurniture.furnitureMechanic(entity)?.itemID)) {
            getCountingData(profile)?.progress()
            completeIfDoneOrNotify(profile)
        }
    }
}