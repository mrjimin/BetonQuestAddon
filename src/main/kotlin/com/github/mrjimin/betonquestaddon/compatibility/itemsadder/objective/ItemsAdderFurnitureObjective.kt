package com.github.mrjimin.betonquestaddon.compatibility.itemsadder.objective

import com.github.mrjimin.betonquestaddon.betonquest.objective.AbstractAddonObjective
import com.github.mrjimin.betonquestaddon.config.NotifyMessage
import dev.lone.itemsadder.api.CustomFurniture
import dev.lone.itemsadder.api.Events.FurnitureBreakEvent
import dev.lone.itemsadder.api.Events.FurnitureInteractEvent
import dev.lone.itemsadder.api.Events.FurniturePlacedEvent
import org.betonquest.betonquest.api.instruction.Argument
import org.betonquest.betonquest.api.quest.objective.service.ObjectiveService
import org.bukkit.Location

class ItemsAdderFurnitureObjective(
    service: ObjectiveService,
    targetAmount: Argument<Number>,
    identifier: Argument<List<String>>,
    isCancelled: Argument<Boolean>,
    location: Argument<Location>?,
    range: Argument<Number>?,
    notifyMessage: NotifyMessage
) : AbstractAddonObjective<CustomFurniture?>(service, targetAmount, identifier, isCancelled, location, range, notifyMessage) {

    fun onPlace(event: FurniturePlacedEvent) {
        handle(event.player, event.furniture, event)
    }

    fun onBreak(event: FurnitureBreakEvent) {
        handle(event.player, event.furniture, event)
    }

    fun onInteract(event: FurnitureInteractEvent) {
        handle(event.player, event.furniture, event)
    }

    override fun getId(target: CustomFurniture?): String? {
        return target?.namespacedID
    }

    override fun getLocation(target: CustomFurniture?): Location {
        return target?.entity?.location!!
    }


}