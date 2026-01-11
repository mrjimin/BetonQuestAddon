package com.github.mrjimin.betonquestaddon.objective

import com.github.mrjimin.betonquestaddon.config.NotifyMessage
import org.betonquest.betonquest.api.CountingObjective
import org.betonquest.betonquest.api.instruction.Argument
import org.betonquest.betonquest.api.quest.objective.service.ObjectiveService
import org.bukkit.entity.Player

abstract class AbstractTargetsObjective(
    service: ObjectiveService,
    targetAmount: Argument<Number>,
    private val identifiers: Argument<List<String>>,
    private val targetIds: Argument<List<String>>,
    notifyMessage: NotifyMessage
) : CountingObjective(service, targetAmount, notifyMessage.toKey()) {

    protected fun handle(player: Player, identifiersId: String, targetId: String) {
        val profile = service.profileProvider.getProfile(player) ?: return
        if (!service.containsProfile(profile) || !service.checkConditions(profile)) return

        if (!identifiers.getValue(profile).contains(identifiersId)) return

        val allowedTargets = targetIds.getValue(profile)
        if (allowedTargets.isEmpty() || allowedTargets.contains(targetId)) {
            getCountingData(profile)?.progress()
            completeIfDoneOrNotify(profile)
        }
    }
}