package com.github.mrjimin.betonquestaddon.compatibility.customcrops.objective.sprinkler

import com.github.mrjimin.betonquestaddon.betonquest.objective.SimpleTargetsObjective
import com.github.mrjimin.betonquestaddon.config.NotifyMessage
import net.momirealms.customcrops.api.event.SprinklerBreakEvent
import net.momirealms.customcrops.api.event.SprinklerPlaceEvent
import org.betonquest.betonquest.api.instruction.Argument
import org.betonquest.betonquest.api.quest.objective.service.ObjectiveService
import org.bukkit.entity.Player

class SprinklerObjective(
    service: ObjectiveService,
    targetAmount: Argument<Number>,
    identifiers: Argument<List<String>>,
    notifyMessage: NotifyMessage
) : SimpleTargetsObjective(service, targetAmount, identifiers, notifyMessage) {

    fun onPlace(event: SprinklerPlaceEvent) {
        wildcardHandle(event.player, event.sprinklerConfig().id())
    }

    fun onBreak(event: SprinklerBreakEvent) {
        val player = event.entityBreaker() as? Player ?: return
        wildcardHandle(player, event.sprinklerConfig().id())
    }
}