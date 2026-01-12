package com.github.mrjimin.betonquestaddon.compatibility.customfishing.objective

import com.github.mrjimin.betonquestaddon.betonquest.objective.SimpleTargetsObjective
import com.github.mrjimin.betonquestaddon.config.NotifyMessage
import net.momirealms.customfishing.api.event.FishingResultEvent
import org.betonquest.betonquest.api.instruction.Argument
import org.betonquest.betonquest.api.quest.objective.service.ObjectiveService

class CaughtFishObjective(
    service: ObjectiveService,
    targetAmount: Argument<Number>,
    identifiers: Argument<List<String>>,
    notifyMessage: NotifyMessage
) : SimpleTargetsObjective(service, targetAmount, identifiers, notifyMessage) {

    fun onFish(event: FishingResultEvent) {
        if (event.result == FishingResultEvent.Result.FAILURE) return
        wildcardHandle(event.player, event.loot.id())
    }

    fun onFishGroup(event: FishingResultEvent) {
        if (event.result == FishingResultEvent.Result.FAILURE) return
        val groups = event.loot.lootGroup()
        for (group in groups) {
            wildcardHandle(event.player, group)
        }
    }
}