package com.github.mrjimin.betonquestaddon.compatibility.nexo.objective

import com.github.mrjimin.betonquestaddon.config.NotifyMessage
import com.nexomc.nexo.api.NexoBlocks
import com.nexomc.nexo.api.events.custom_block.NexoBlockBreakEvent
import com.nexomc.nexo.api.events.custom_block.NexoBlockInteractEvent
import com.nexomc.nexo.api.events.custom_block.NexoBlockPlaceEvent
import org.betonquest.betonquest.api.CountingObjective
import org.betonquest.betonquest.api.QuestException
import org.betonquest.betonquest.api.instruction.Argument
import org.betonquest.betonquest.api.quest.objective.service.ObjectiveService
import org.bukkit.block.Block
import org.bukkit.entity.Player
import org.bukkit.event.Cancellable

class NexoBlockObjective(
    service: ObjectiveService,
    targetAmount: Argument<Number>,
    private val identifier: Argument<List<String>>,
    private val isCancelled: Argument<Boolean>,
    notifyMessage: NotifyMessage
) : CountingObjective(service, targetAmount, notifyMessage.toKey()) {

    fun onPlace(event: NexoBlockPlaceEvent) {
        handle(event.player, event.block, event)
    }

    fun onBreak(event: NexoBlockBreakEvent) {
        handle(event.player, event.block, event)
    }

    fun onInteract(event: NexoBlockInteractEvent) {
        handle(event.player, event.block, event)
    }

    @Throws(QuestException::class)
    private fun handle(player: Player, block: Block, event: Cancellable) {
        val profile = service.profileProvider.getProfile(player) ?: return
        if (!service.containsProfile(profile) || !service.checkConditions(profile)) return

        if (identifier.getValue(profile).contains(NexoBlocks.customBlockMechanic(block)?.itemID)) {
            getCountingData(profile)?.progress()
            completeIfDoneOrNotify(profile)

            if (isCancelled.getValue(profile)) event.isCancelled = true
        }
    }
}