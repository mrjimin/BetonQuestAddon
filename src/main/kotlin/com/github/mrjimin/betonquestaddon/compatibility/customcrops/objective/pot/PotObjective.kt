package com.github.mrjimin.betonquestaddon.compatibility.customcrops.objective.pot

import com.github.mrjimin.betonquestaddon.compatibility.customcrops.objective.CustomCropsObjective
import com.github.mrjimin.betonquestaddon.config.NotifyMessage
import net.momirealms.customcrops.api.event.PotBreakEvent
import net.momirealms.customcrops.api.event.PotPlaceEvent
import org.betonquest.betonquest.api.instruction.Argument
import org.betonquest.betonquest.api.quest.objective.service.ObjectiveService
import org.bukkit.entity.Player

class PotObjective(
    service: ObjectiveService,
    targetAmount: Argument<Number>,
    identifiers: Argument<List<String>>,
    notifyMessage: NotifyMessage
) : CustomCropsObjective(service, targetAmount, identifiers, notifyMessage) {

    fun onPlace(event: PotPlaceEvent) {
        handle(event.player, event.potConfig().id())
    }

    fun onBreak(event: PotBreakEvent) {
        handle(event.entityBreaker() as? Player ?: return, event.potConfig().id())
    }

}