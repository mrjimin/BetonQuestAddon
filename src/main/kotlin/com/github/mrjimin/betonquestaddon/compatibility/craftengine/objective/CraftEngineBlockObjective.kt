package com.github.mrjimin.betonquestaddon.compatibility.craftengine.objective

import com.github.mrjimin.betonquestaddon.betonquest.objective.AbstractAddonObjective
import com.github.mrjimin.betonquestaddon.config.NotifyMessage
import net.momirealms.craftengine.bukkit.api.BukkitAdaptors
import net.momirealms.craftengine.bukkit.api.event.CustomBlockBreakEvent
import net.momirealms.craftengine.bukkit.api.event.CustomBlockInteractEvent
import net.momirealms.craftengine.bukkit.api.event.CustomBlockPlaceEvent
import org.betonquest.betonquest.api.instruction.Argument
import org.betonquest.betonquest.api.quest.objective.service.ObjectiveService
import org.bukkit.Location
import org.bukkit.block.Block

class CraftEngineBlockObjective(
    service: ObjectiveService,
    targetAmount: Argument<Number>,
    identifier: Argument<List<String>>,
    isCancelled: Argument<Boolean>,
    location: Argument<Location>?,
    range: Argument<Number>,
    notifyMessage: NotifyMessage
) : AbstractAddonObjective<Block>(service, targetAmount, identifier, isCancelled, location, range, notifyMessage) {

    fun onPlace(event: CustomBlockPlaceEvent) {
        handle(event.player, event.bukkitBlock(), event)
    }

    fun onBreak(event: CustomBlockBreakEvent) {
        handle(event.player, event.bukkitBlock(), event)
    }

    fun onInteract(event: CustomBlockInteractEvent) {
        handle(event.player, event.bukkitBlock(), event)
    }

    override fun getId(target: Block): String {
        return BukkitAdaptors.adapt(target).id().toString()
    }

    override fun getLocation(target: Block): Location {
        return target.location
    }

}