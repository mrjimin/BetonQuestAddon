package com.github.mrjimin.betonquestaddon.compatibility.customcrops.objective.common

import com.github.mrjimin.betonquestaddon.config.NotifyMessage
import com.github.mrjimin.betonquestaddon.objective.SimpleTargetsObjective
import net.momirealms.customcrops.api.event.ScarecrowBreakEvent
import net.momirealms.customcrops.api.event.ScarecrowPlaceEvent
import org.betonquest.betonquest.api.instruction.Argument
import org.betonquest.betonquest.api.quest.objective.service.ObjectiveService
import org.bukkit.entity.Player

class ScarecrowObjective(
    service: ObjectiveService,
    targetAmount: Argument<Number>,
    identifiers: Argument<List<String>>,
    notifyMessage: NotifyMessage
) : SimpleTargetsObjective(service, targetAmount, identifiers, notifyMessage) {

    fun onPlace(event: ScarecrowPlaceEvent) {
        handle(event.player, event.scarecrowItemID())
    }

    fun onBreak(event: ScarecrowBreakEvent) {
        val player = event.entityBreaker() as? Player ?: return
        handle(player, event.scarecrowItemID())
    }

}