package com.github.mrjimin.betonquestaddon.compatibility.customcrops.objective.wateringcan

import com.github.mrjimin.betonquestaddon.betonquest.objective.TargetsObjective
import com.github.mrjimin.betonquestaddon.config.NotifyMessage
import net.momirealms.customcrops.api.event.WateringCanWaterPotEvent
import org.betonquest.betonquest.api.instruction.Argument
import org.betonquest.betonquest.api.quest.objective.service.ObjectiveService

class CanPotObjective(
    service: ObjectiveService,
    targetAmount: Argument<Number>,
    identifiers: Argument<List<String>>,
    potIds: Argument<List<String>>
) : TargetsObjective(service, targetAmount, identifiers, potIds, NotifyMessage.CUSTOM_CROPS_CAN_POT) {

    fun onWateringPot(event: WateringCanWaterPotEvent) {
        handle(event.player, event.wateringCanConfig().id(), event.potConfig().id())
    }
}