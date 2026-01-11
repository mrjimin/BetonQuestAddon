package com.github.mrjimin.betonquestaddon.compatibility.customcrops.objective.fertilizer

import com.github.mrjimin.betonquestaddon.config.NotifyMessage
import net.momirealms.customcrops.api.event.FertilizerUseEvent
import org.betonquest.betonquest.api.CountingObjective
import org.betonquest.betonquest.api.QuestException
import org.betonquest.betonquest.api.instruction.Argument
import org.betonquest.betonquest.api.quest.objective.service.ObjectiveService
import kotlin.jvm.Throws

class FertilizerUseObjective(
    service: ObjectiveService,
    targetAmount: Argument<Number>,
    private val identifiers: Argument<List<String>>,
    private val potIds: Argument<List<String>>
) : CountingObjective(service, targetAmount, NotifyMessage.CUSTOM_CROPS_USE_FERTILIZER.toKey()) {

    @Throws(QuestException::class)
    fun onUseFertilizer(event: FertilizerUseEvent) {
        val profile = service.profileProvider.getProfile(event.player) ?: return
        if (!service.containsProfile(profile) || !service.checkConditions(profile)) return

        if (!identifiers.getValue(profile).contains(event.fertilizer().id())) {
            return;
        }

        val allowPots = potIds.getValue(profile)
        if (allowPots.isEmpty() || allowPots.contains(event.potConfig().id())) {
            getCountingData(profile)?.progress()
            completeIfDoneOrNotify(profile)
        }
    }

}