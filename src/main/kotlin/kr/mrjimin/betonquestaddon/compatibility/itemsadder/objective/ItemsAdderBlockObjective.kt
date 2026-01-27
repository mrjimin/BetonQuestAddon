package kr.mrjimin.betonquestaddon.compatibility.itemsadder.objective

import dev.lone.itemsadder.api.CustomBlock
import dev.lone.itemsadder.api.Events.CustomBlockBreakEvent
import dev.lone.itemsadder.api.Events.CustomBlockInteractEvent
import dev.lone.itemsadder.api.Events.CustomBlockPlaceEvent
import kr.mrjimin.betonquestaddon.betonquest.objective.AbstractAddonObjective
import kr.mrjimin.betonquestaddon.config.NotifyMessage
import org.betonquest.betonquest.api.instruction.Argument
import org.betonquest.betonquest.api.quest.objective.service.ObjectiveService
import org.bukkit.Location
import org.bukkit.block.Block

class ItemsAdderBlockObjective(
    service: ObjectiveService,
    targetAmount: Argument<Number>,
    identifier: Argument<List<String>>,
    isCancelled: Argument<Boolean>,
    location: Argument<Location>?,
    range: Argument<Number>,
    notifyMessage: NotifyMessage
) : AbstractAddonObjective<Block>(service, targetAmount, identifier, isCancelled, location, range, notifyMessage) {

    fun onPlace(event: CustomBlockPlaceEvent) {
        handle(event.player, event.block, event)
    }

    fun onBreak(event: CustomBlockBreakEvent) {
        handle(event.player, event.block, event)
    }

    fun onInteract(event: CustomBlockInteractEvent) {
        handle(event.player, event.blockClicked, event)
    }

    override fun getId(target: Block): String? {
        return CustomBlock.byAlreadyPlaced(target)?.namespacedID
    }

    override fun getLocation(target: Block): Location {
        return target.location
    }

}