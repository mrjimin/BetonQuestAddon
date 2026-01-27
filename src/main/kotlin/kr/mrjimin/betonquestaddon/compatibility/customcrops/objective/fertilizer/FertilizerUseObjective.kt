package kr.mrjimin.betonquestaddon.compatibility.customcrops.objective.fertilizer

import kr.mrjimin.betonquestaddon.betonquest.objective.TargetsObjective
import kr.mrjimin.betonquestaddon.config.NotifyMessage
import net.momirealms.customcrops.api.event.FertilizerUseEvent
import org.betonquest.betonquest.api.QuestException
import org.betonquest.betonquest.api.instruction.Argument
import org.betonquest.betonquest.api.quest.objective.service.ObjectiveService

class FertilizerUseObjective(
    service: ObjectiveService,
    targetAmount: Argument<Number>,
    private val identifiers: Argument<List<String>>,
    private val pots: Argument<List<String>>
) : TargetsObjective(service, targetAmount, identifiers, pots, NotifyMessage.CUSTOM_CROPS_USE_FERTILIZER) {

    @Throws(QuestException::class)
    fun onUseFertilizer(event: FertilizerUseEvent) {
//        val profile = service.profileProvider.getProfile(event.player) ?: return
//        if (!service.containsProfile(profile) || !service.checkConditions(profile)) return
//
//        if (!getMatcher(identifiers.getValue(profile)).matches(event.fertilizer().id())) return
//
//        val potPatterns = pots.getValue(profile)
//        if (potPatterns.isEmpty() || getMatcher(potPatterns).matches(event.potConfig().id())) {
//            getCountingData(profile)?.progress()
//            completeIfDoneOrNotify(profile)
//        }
        wildcardHandle(event.player, event.fertilizer().id(), event.potConfig().id())
    }

}