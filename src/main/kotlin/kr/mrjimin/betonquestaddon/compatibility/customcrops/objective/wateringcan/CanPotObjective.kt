package kr.mrjimin.betonquestaddon.compatibility.customcrops.objective.wateringcan

import kr.mrjimin.betonquestaddon.config.NotifyMessage
import kr.mrjimin.betonquestaddon.util.DefaultOptions
import kr.mrjimin.betonquestaddon.util.DualIdTarget
import net.momirealms.customcrops.api.event.WateringCanWaterPotEvent
import org.betonquest.betonquest.api.CountingObjective
import org.betonquest.betonquest.api.instruction.Argument
import org.betonquest.betonquest.api.profile.OnlineProfile
import org.betonquest.betonquest.api.quest.objective.service.ObjectiveService

class CanPotObjective(
    service: ObjectiveService,
    private val options: DefaultOptions,
    notifyMessage: NotifyMessage,
    private val id: Argument<List<String>>,
    private val potId: Argument<List<String>>? = null
) : CountingObjective(service, options.amount, notifyMessage.toKey()) {

    fun onWateringPot(event: WateringCanWaterPotEvent, profile: OnlineProfile) {
        handle(
            profile,
            DualIdTarget(
                event.wateringCanConfig().id(),
                event.potConfig().id()
            ),
            event
        )
    }

    fun handle(profile: OnlineProfile, target: DualIdTarget, event: WateringCanWaterPotEvent) {
        if (options.isCancelled.getValue(profile)) {
            event.isCancelled = true
            return
        }

        potId?.getValue(profile)?.equals(target.targetId)?.let { if (!it) return }

        if (id.getValue(profile).equals(target.id)) {
            getCountingData(profile)?.progress()
            completeIfDoneOrNotify(profile)
        }
    }
}