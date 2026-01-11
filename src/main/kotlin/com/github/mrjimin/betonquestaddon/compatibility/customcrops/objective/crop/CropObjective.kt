package com.github.mrjimin.betonquestaddon.compatibility.customcrops.objective.crop

import com.github.mrjimin.betonquestaddon.compatibility.customcrops.objective.CustomCropsObjective
import com.github.mrjimin.betonquestaddon.config.NotifyMessage
import net.momirealms.customcrops.api.event.CropBreakEvent
import net.momirealms.customcrops.api.event.CropPlantEvent
import org.betonquest.betonquest.api.instruction.Argument
import org.betonquest.betonquest.api.quest.objective.service.ObjectiveService
import org.bukkit.entity.Player

class CropObjective(
    service: ObjectiveService,
    targetAmount: Argument<Number>,
    identifiers: Argument<List<String>>,
    notifyMessage: NotifyMessage
) : CustomCropsObjective(service, targetAmount, identifiers, notifyMessage) {

    fun onPlace(event: CropPlantEvent) {
        handle(event.player, event.cropConfig().id())
    }

    fun onBreak(event: CropBreakEvent) {
        handle(event.entityBreaker() as? Player ?: return, event.cropConfig().id())
    }
}