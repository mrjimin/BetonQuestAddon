package com.github.mrjimin.betonquestaddon.compatibility.customcrops.objective

import com.github.mrjimin.betonquestaddon.config.NotifyMessage
import org.betonquest.betonquest.api.CountingObjective
import org.betonquest.betonquest.api.QuestException
import org.betonquest.betonquest.api.instruction.Argument
import org.betonquest.betonquest.api.quest.objective.service.ObjectiveService
import org.bukkit.entity.Player

abstract class CustomCropsObjective(
    service: ObjectiveService,
    targetAmount: Argument<Number>,
    private val identifiers: Argument<List<String>>,
    notifyMessage: NotifyMessage
) : CountingObjective(service, targetAmount, notifyMessage.toKey()) {

    @Throws(QuestException::class)
    protected fun handle(player: Player, configId: String) {
        val profile = service.profileProvider.getProfile(player) ?: return
        if (!service.containsProfile(profile) || !service.checkConditions(profile)) return

        if (identifiers.getValue(profile).contains(configId)) {
            getCountingData(profile)?.progress()
            completeIfDoneOrNotify(profile)
        }
    }
}