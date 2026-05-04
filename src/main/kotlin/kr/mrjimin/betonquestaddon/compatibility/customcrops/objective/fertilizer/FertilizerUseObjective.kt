package kr.mrjimin.betonquestaddon.compatibility.customcrops.objective.fertilizer

import kr.mrjimin.betonquestaddon.betonquest.objective.AddonObjective
import kr.mrjimin.betonquestaddon.config.NotifyMessage
import kr.mrjimin.betonquestaddon.util.DualIdTarget
import kr.mrjimin.betonquestaddon.util.ObjectiveOptions
import net.momirealms.customcrops.api.event.FertilizerUseEvent
import org.betonquest.betonquest.api.instruction.Argument
import org.betonquest.betonquest.api.profile.OnlineProfile
import org.betonquest.betonquest.api.quest.objective.service.ObjectiveService

class FertilizerUseObjective(
    service: ObjectiveService,
    amount: Argument<Number>,
    options: ObjectiveOptions
) : AddonObjective<DualIdTarget>(service, amount, options, NotifyMessage.CUSTOM_CROPS_USE_FERTILIZER) {

    fun onUseFertilizer(event: FertilizerUseEvent, profile: OnlineProfile) {
        handle(
            profile,
            DualIdTarget(
                event.fertilizer().id(),
                event.potConfig().id()
            )
        )
    }

    override fun getId(target: DualIdTarget): String = target.id

    override fun getTargetId(target: DualIdTarget): String = target.targetId

}