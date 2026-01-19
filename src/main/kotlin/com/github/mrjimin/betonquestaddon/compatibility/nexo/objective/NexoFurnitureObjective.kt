package com.github.mrjimin.betonquestaddon.compatibility.nexo.objective

import com.github.mrjimin.betonquestaddon.betonquest.objective.AbstractAddonObjective
import com.github.mrjimin.betonquestaddon.config.NotifyMessage
import com.nexomc.nexo.api.NexoFurniture
import com.nexomc.nexo.api.events.furniture.NexoFurnitureBreakEvent
import com.nexomc.nexo.api.events.furniture.NexoFurnitureInteractEvent
import com.nexomc.nexo.api.events.furniture.NexoFurniturePlaceEvent
import org.betonquest.betonquest.api.instruction.Argument
import org.betonquest.betonquest.api.quest.objective.service.ObjectiveService
import org.bukkit.Location
import org.bukkit.entity.Entity

class NexoFurnitureObjective(
    service: ObjectiveService,
    targetAmount: Argument<Number>,
    identifier: Argument<List<String>>,
    isCancelled: Argument<Boolean>,
    location: Argument<Location>?,
    range: Argument<Number>?,
    notifyMessage: NotifyMessage,
) : AbstractAddonObjective<Entity>(service, targetAmount, identifier, isCancelled, location, range, notifyMessage) {

    fun onPlace(event: NexoFurniturePlaceEvent) {
        handle(event.player, event.baseEntity, event)
    }

    fun onBreak(event: NexoFurnitureBreakEvent) {
        handle(event.player, event.baseEntity, event)
    }

    fun onInteract(event: NexoFurnitureInteractEvent) {
        handle(event.player, event.baseEntity, event)
    }

    override fun getId(target: Entity): String? {
        return NexoFurniture.furnitureMechanic(target)?.itemID
    }

    override fun getLocation(target: Entity): Location {
        return target.location
    }

}