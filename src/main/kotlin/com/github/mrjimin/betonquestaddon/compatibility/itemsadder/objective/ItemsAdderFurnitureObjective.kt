package com.github.mrjimin.betonquestaddon.compatibility.itemsadder.objective

import com.github.mrjimin.betonquestaddon.config.NotifyMessage
import dev.lone.itemsadder.api.CustomFurniture
import dev.lone.itemsadder.api.Events.FurnitureBreakEvent
import dev.lone.itemsadder.api.Events.FurnitureInteractEvent
import dev.lone.itemsadder.api.Events.FurniturePlacedEvent
import org.betonquest.betonquest.api.CountingObjective
import org.betonquest.betonquest.api.QuestException
import org.betonquest.betonquest.api.instruction.Argument
import org.betonquest.betonquest.api.quest.objective.service.ObjectiveService
import org.bukkit.entity.Player
import org.bukkit.event.Cancellable

class ItemsAdderFurnitureObjective(
    service: ObjectiveService,
    targetAmount: Argument<Number>,
    private val identifier: Argument<List<String>>,
    private val isCancelled: Argument<Boolean>,
    notifyMessage: NotifyMessage
) : CountingObjective(service, targetAmount, notifyMessage.toKey()) {

    fun onPlace(event: FurniturePlacedEvent) {
        handle(event.player, event.furniture, event)
    }

    fun onBreak(event: FurnitureBreakEvent) {
        handle(event.player, event.furniture, event)
    }

    fun onInteract(event: FurnitureInteractEvent) {
        handle(event.player, event.furniture, event)
    }

    @Throws(QuestException::class)
    private fun handle(player: Player, customFurniture: CustomFurniture?, event: Cancellable) {
        val profile = service.profileProvider.getProfile(player) ?: return
        if (!service.containsProfile(profile) || !service.checkConditions(profile)) return

        if (identifier.getValue(profile).contains(customFurniture?.namespacedID)) {
            getCountingData(profile)?.progress()
            completeIfDoneOrNotify(profile)

            if (isCancelled.getValue(profile)) event.isCancelled = true
        }
    }


}