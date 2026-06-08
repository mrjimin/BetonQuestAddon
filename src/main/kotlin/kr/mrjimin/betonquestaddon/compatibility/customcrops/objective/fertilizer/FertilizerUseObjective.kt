package kr.mrjimin.betonquestaddon.compatibility.customcrops.objective.fertilizer

import kr.mrjimin.betonquestaddon.betonquest.objective.AddonObjective
import kr.mrjimin.betonquestaddon.config.NotifyMessage
import kr.mrjimin.betonquestaddon.util.DefaultOptions
import kr.mrjimin.betonquestaddon.util.DualIdTarget
import net.momirealms.customcrops.api.event.FertilizerUseEvent
import org.betonquest.betonquest.api.instruction.Argument
import org.betonquest.betonquest.api.profile.OnlineProfile
import org.betonquest.betonquest.api.quest.objective.service.ObjectiveService

class FertilizerUseObjective(
    service: ObjectiveService,
    options: DefaultOptions,
    notifyMessage: NotifyMessage,
    private val id: Argument<List<String>>,
    private val potId: Argument<List<String>>? = null
) : AddonObjective(service, options, notifyMessage) {

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

    private fun handle(profile: OnlineProfile, target: DualIdTarget, event: FertilizerUseEvent) {
        potId?.getValue(profile)?.equals(target.targetId)?.let { if (!it) return }
        success(profile, id.getValue(profile).equals(target.id), event, event.location())
    }
}