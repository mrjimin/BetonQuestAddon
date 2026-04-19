package kr.mrjimin.betonquestaddon.compatibility.customcrops.objective.fertilizer

import kr.mrjimin.betonquestaddon.betonquest.objective.TargetsObjective
import kr.mrjimin.betonquestaddon.config.NotifyMessage
import net.momirealms.customcrops.api.event.FertilizerUseEvent
import org.betonquest.betonquest.api.QuestException
import org.betonquest.betonquest.api.instruction.Argument
import org.betonquest.betonquest.api.profile.OnlineProfile
import org.betonquest.betonquest.api.quest.objective.service.ObjectiveService

class FertilizerUseObjective(
    service: ObjectiveService,
    targetAmount: Argument<Number>,
    identifiers: Argument<List<String>>,
    pots: Argument<List<String>>
) : TargetsObjective(service, targetAmount, identifiers, pots, NotifyMessage.CUSTOM_CROPS_USE_FERTILIZER) {

    fun onUseFertilizer(event: FertilizerUseEvent, profile: OnlineProfile) {
        wildcardHandle(profile, event.fertilizer().id(), event.potConfig().id())
    }

}