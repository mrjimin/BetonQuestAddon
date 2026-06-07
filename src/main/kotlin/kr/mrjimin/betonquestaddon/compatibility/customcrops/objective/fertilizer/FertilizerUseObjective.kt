package kr.mrjimin.betonquestaddon.compatibility.customcrops.objective.fertilizer

import kr.mrjimin.betonquestaddon.config.NotifyMessage
import kr.mrjimin.betonquestaddon.util.DefaultOptions
import kr.mrjimin.betonquestaddon.util.DualIdTarget
import net.momirealms.customcrops.api.event.FertilizerUseEvent
import org.betonquest.betonquest.api.CountingObjective
import org.betonquest.betonquest.api.instruction.Argument
import org.betonquest.betonquest.api.profile.OnlineProfile
import org.betonquest.betonquest.api.quest.objective.service.ObjectiveService

class FertilizerUseObjective(
    service: ObjectiveService,
    private val options: DefaultOptions,
    notifyMessage: NotifyMessage,
    private val id: Argument<List<String>>,
    private val potId: Argument<List<String>>? = null
) : CountingObjective(service, options.amount, notifyMessage.toKey()) {

    fun onUseFertilizer(event: FertilizerUseEvent, profile: OnlineProfile) {
        handle(
            profile,
            DualIdTarget(
                event.fertilizer().id(),
                event.potConfig().id()
            ),
            event
        )
    }

    fun handle(profile: OnlineProfile, target: DualIdTarget, event: FertilizerUseEvent) {
        options.locationFilter?.let { filter ->
            if (!filter.matches(profile, event.location())) return
        }

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