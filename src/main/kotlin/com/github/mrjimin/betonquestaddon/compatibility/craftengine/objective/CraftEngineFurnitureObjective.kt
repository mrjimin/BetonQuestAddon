package com.github.mrjimin.betonquestaddon.compatibility.craftengine.objective

import com.github.mrjimin.betonquestaddon.betonquest.objective.AbstractAddonObjective
import com.github.mrjimin.betonquestaddon.config.NotifyMessage
import net.momirealms.craftengine.bukkit.api.event.FurnitureBreakEvent
import net.momirealms.craftengine.bukkit.api.event.FurnitureInteractEvent
import net.momirealms.craftengine.bukkit.api.event.FurniturePlaceEvent
import net.momirealms.craftengine.bukkit.entity.furniture.BukkitFurniture
import org.betonquest.betonquest.api.instruction.Argument
import org.betonquest.betonquest.api.quest.objective.service.ObjectiveService
import org.bukkit.Location

class CraftEngineFurnitureObjective(
    service: ObjectiveService,
    targetAmount: Argument<Number>,
    identifier: Argument<List<String>>,
    isCancelled: Argument<Boolean>,
    location: Argument<Location>?,
    range: Argument<Number>?,
    notifyMessage: NotifyMessage
) : AbstractAddonObjective<BukkitFurniture>(service, targetAmount, identifier, isCancelled, location, range, notifyMessage) {

    fun onPlace(event: FurniturePlaceEvent) {
        handle(event.player, event.furniture(), event)
    }

    fun onBreak(event: FurnitureBreakEvent) {
        handle(event.player, event.furniture(), event)
    }

    fun onInteract(event: FurnitureInteractEvent) {
        handle(event.player, event.furniture(), event)
    }

    override fun getId(target: BukkitFurniture): String {
        return target.id().toString()
    }

    override fun getLocation(target: BukkitFurniture): Location {
        return target.location()
    }

}