package com.github.mrjimin.betonquestaddon.compatibility.nexo.objective

import com.github.mrjimin.betonquestaddon.betonquest.objective.AbstractAddonObjective
import com.github.mrjimin.betonquestaddon.config.NotifyMessage
import com.nexomc.nexo.api.NexoBlocks
import com.nexomc.nexo.api.events.custom_block.NexoBlockBreakEvent
import com.nexomc.nexo.api.events.custom_block.NexoBlockInteractEvent
import com.nexomc.nexo.api.events.custom_block.NexoBlockPlaceEvent
import org.betonquest.betonquest.api.instruction.Argument
import org.betonquest.betonquest.api.quest.objective.service.ObjectiveService
import org.bukkit.Location
import org.bukkit.block.Block

class NexoBlockObjective(
    service: ObjectiveService,
    targetAmount: Argument<Number>,
    identifier: Argument<List<String>>,
    isCancelled: Argument<Boolean>,
    location: Argument<Location>?,
    range: Argument<Number>,
    notifyMessage: NotifyMessage
) : AbstractAddonObjective<Block>(service, targetAmount, identifier, isCancelled, location, range, notifyMessage) {

    override fun getId(target: Block): String? {
        return NexoBlocks.customBlockMechanic(target)?.itemID
    }
    override fun getLocation(target: Block): Location {
        return target.location
    }

    fun onPlace(event: NexoBlockPlaceEvent) {
        handle(event.player, event.block, event)
    }

    fun onBreak(event: NexoBlockBreakEvent) {
        handle(event.player, event.block, event)
    }

    fun onInteract(event: NexoBlockInteractEvent) {
        handle(event.player, event.block, event)
    }
}