package com.github.mrjimin.betonquestaddon.compatibility.itemsadder.objective

import com.github.mrjimin.betonquestaddon.config.NotifyMessage
import com.github.mrjimin.betonquestaddon.util.action.Action
import dev.lone.itemsadder.api.CustomFurniture
import dev.lone.itemsadder.api.Events.FurnitureBreakEvent
import dev.lone.itemsadder.api.Events.FurnitureInteractEvent
import dev.lone.itemsadder.api.Events.FurniturePlacedEvent
import org.betonquest.betonquest.api.CountingObjective
import org.betonquest.betonquest.api.QuestException
import org.betonquest.betonquest.api.instruction.Argument
import org.betonquest.betonquest.api.quest.objective.service.ObjectiveService
import org.bukkit.entity.Player

class ItemsAdderFurnitureObjective(
    service: ObjectiveService,
    targetAmount: Argument<Number>,
    // private val identifier: Argument<String>,
    private val identifier: Argument<List<String>>,
    notifyMessage: NotifyMessage
) : CountingObjective(service, targetAmount, notifyMessage.toKey()) {

    fun onPlace(event: FurniturePlacedEvent) {
        handle(event.player, event.furniture)
    }

    fun onBreak(event: FurnitureBreakEvent) {
        handle(event.player, event.furniture)
    }

    fun onInteract(event: FurnitureInteractEvent) {
        handle(event.player, event.furniture)
    }

    @Throws(QuestException::class)
    private fun handle(player: Player, customFurniture: CustomFurniture?) {
        val profile = service.profileProvider.getProfile(player) ?: return
        if (!service.containsProfile(profile) || !service.checkConditions(profile)) return

        // if (identifier.getValue(profile) == customFurniture?.namespacedID) {
        if (identifier.getValue(profile).contains(customFurniture?.namespacedID)) {
            getCountingData(profile)?.progress()
            completeIfDoneOrNotify(profile)
        }
    }
}