package com.github.mrjimin.betonquestaddon.compatibility.customcrops.objective.common

import com.github.mrjimin.betonquestaddon.compatibility.customcrops.objective.CustomCropsObjective
import com.github.mrjimin.betonquestaddon.config.NotifyMessage
import net.momirealms.customcrops.api.event.*
import org.betonquest.betonquest.api.instruction.Argument
import org.betonquest.betonquest.api.quest.objective.service.ObjectiveService
import org.bukkit.entity.Player

class ScarecrowObjective(
    service: ObjectiveService,
    targetAmount: Argument<Number>,
    identifiers: Argument<List<String>>,
    notifyMessage: NotifyMessage
) : CustomCropsObjective(service, targetAmount, identifiers, notifyMessage) {

    fun onPlace(event: ScarecrowPlaceEvent) {
        handle(event.player, event.scarecrowItemID())
    }

    fun onBreak(event: ScarecrowBreakEvent) {
        handle(event.entityBreaker() as? Player ?: return, event.scarecrowItemID())
    }
}