package com.github.mrjimin.betonquestaddon.compatibility.customcrops.objective.crop

import com.github.mrjimin.betonquestaddon.betonquest.objective.SimpleTargetsObjective
import com.github.mrjimin.betonquestaddon.config.NotifyMessage
import net.momirealms.customcrops.api.event.CropBreakEvent
import net.momirealms.customcrops.api.event.CropPlantEvent
import org.betonquest.betonquest.api.QuestException
import org.betonquest.betonquest.api.instruction.Argument
import org.betonquest.betonquest.api.quest.objective.service.ObjectiveService
import org.bukkit.entity.Player

class CropObjective(
    service: ObjectiveService,
    targetAmount: Argument<Number>,
    identifiers: Argument<List<String>>,
    notifyMessage: NotifyMessage
) : SimpleTargetsObjective(service, targetAmount, identifiers, notifyMessage) {

    @Throws(QuestException::class)
    fun onPlace(event: CropPlantEvent) {
        handle(event.player, event.cropConfig().id())
    }

    @Throws(QuestException::class)
    fun onBreak(event: CropBreakEvent) {
        val player = event.entityBreaker() as? Player ?: return
        wildcardHandle(player, event.cropStageItemID())
    }

}