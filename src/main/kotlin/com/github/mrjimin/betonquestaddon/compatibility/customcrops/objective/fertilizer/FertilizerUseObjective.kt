package com.github.mrjimin.betonquestaddon.compatibility.customcrops.objective.fertilizer

import com.github.mrjimin.betonquestaddon.objective.AbstractTargetsObjective
import com.github.mrjimin.betonquestaddon.config.NotifyMessage
import net.momirealms.customcrops.api.event.FertilizerUseEvent
import org.betonquest.betonquest.api.CountingObjective
import org.betonquest.betonquest.api.QuestException
import org.betonquest.betonquest.api.instruction.Argument
import org.betonquest.betonquest.api.quest.objective.service.ObjectiveService

class FertilizerUseObjective(
    service: ObjectiveService,
    targetAmount: Argument<Number>,
    identifiers: Argument<List<String>>,
    pots: Argument<List<String>>
) : AbstractTargetsObjective(service, targetAmount, identifiers, pots, NotifyMessage.CUSTOM_CROPS_USE_FERTILIZER) {

    @Throws(QuestException::class)
    fun onUseFertilizer(event: FertilizerUseEvent) {
        handle(event.player, event.fertilizer().id(), event.potConfig().id())
    }

}