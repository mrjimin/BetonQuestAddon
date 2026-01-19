package com.github.mrjimin.betonquestaddon.betonquest.objective

import com.github.mrjimin.betonquestaddon.config.NotifyMessage
import org.betonquest.betonquest.api.CountingObjective
import org.betonquest.betonquest.api.QuestException
import org.betonquest.betonquest.api.instruction.Argument
import org.betonquest.betonquest.api.quest.objective.service.ObjectiveService
import org.bukkit.Location
import org.bukkit.entity.Player
import org.bukkit.event.Cancellable

abstract class AbstractAddonObjective<T>(
    service: ObjectiveService,
    targetAmount: Argument<Number>,
    private val identifier: Argument<List<String>>,
    private val isCancelled: Argument<Boolean>,
    private val location: Argument<Location>,
    private val range: Argument<Number>,
    notifyMessage: NotifyMessage
) : CountingObjective(service, targetAmount, notifyMessage.toKey()) {

    protected abstract fun getId(target: T): String?
    protected abstract fun getLocation(target: T): Location

    @Throws(QuestException::class)
    protected fun handle(player: Player, target: T, event: Cancellable) {
        val profile = service.profileProvider.getProfile(player) ?: return
        if (!service.containsProfile(profile) || !service.checkConditions(profile)) return

        val id = getId(target)
        if (!identifier.getValue(profile).contains(id)) return

        val targetLoc = location.getValue(profile)
        if (targetLoc != null) {
            val currentLoc = getLocation(target)
            if (currentLoc.world != targetLoc.world) return

            val maxRange = range.getValue(profile).toDouble()
            if (currentLoc.distance(targetLoc) > maxRange) return
        }

        getCountingData(profile)?.progress()
        completeIfDoneOrNotify(profile)

        if (isCancelled.getValue(profile)) {
            event.isCancelled = true
        }
    }
}