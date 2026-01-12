package com.github.mrjimin.betonquestaddon.compatibility.customcrops.objective.wateringcan

import com.github.mrjimin.betonquestaddon.betonquest.objective.TargetsObjective
import com.github.mrjimin.betonquestaddon.config.NotifyMessage
import net.momirealms.customcrops.api.event.WateringCanWaterSprinklerEvent
import org.betonquest.betonquest.api.instruction.Argument
import org.betonquest.betonquest.api.quest.objective.service.ObjectiveService

class CanSprinklerObjective(
    service: ObjectiveService,
    targetAmount: Argument<Number>,
    identifiers: Argument<List<String>>,
    sprinklers: Argument<List<String>>
) : TargetsObjective(service, targetAmount, identifiers, sprinklers, NotifyMessage.CUSTOM_CROPS_CAN_SPRINKLER) {

    fun onWateringSprinkler(event: WateringCanWaterSprinklerEvent) {
        handle(event.player, event.wateringCanConfig().id(), event.sprinklerConfig().id())
    }
}