package com.github.mrjimin.betonquestaddon.compatibility.itemsadder.objective

import com.github.mrjimin.betonquestaddon.config.NotifyMessage
import com.github.mrjimin.betonquestaddon.util.action.Action
import dev.lone.itemsadder.api.CustomBlock
import dev.lone.itemsadder.api.Events.CustomBlockBreakEvent
import dev.lone.itemsadder.api.Events.CustomBlockInteractEvent
import dev.lone.itemsadder.api.Events.CustomBlockPlaceEvent
import org.betonquest.betonquest.api.CountingObjective
import org.betonquest.betonquest.api.QuestException
import org.betonquest.betonquest.api.instruction.Argument
import org.betonquest.betonquest.api.quest.objective.service.ObjectiveService
import org.bukkit.block.Block
import org.bukkit.entity.Player

class ItemsAdderBlockObjective(
    service: ObjectiveService,
    targetAmount: Argument<Number>,
    // private val identifier: Argument<String>,
    private val identifier: Argument<List<String>>,
    notifyMessage: NotifyMessage
) : CountingObjective(service, targetAmount, notifyMessage.toKey()) {

    fun onPlace(event: CustomBlockPlaceEvent) {
        handle(event.player, event.block)
    }

    fun onBreak(event: CustomBlockBreakEvent) {
        handle(event.player, event.block)
    }

    fun onInteract(event: CustomBlockInteractEvent) {
        handle(event.player, event.blockClicked)
    }

    @Throws(QuestException::class)
    private fun handle(player: Player, block: Block) {
        val profile = service.profileProvider.getProfile(player) ?: return
        if (!service.containsProfile(profile) || !service.checkConditions(profile)) return

        // if (identifier.getValue(profile) == CustomBlock.byAlreadyPlaced(block)?.namespacedID) {
        if (identifier.getValue(profile).contains(CustomBlock.byAlreadyPlaced(block)?.namespacedID)) {
            getCountingData(profile)?.progress()
            completeIfDoneOrNotify(profile)
        }
    }
}