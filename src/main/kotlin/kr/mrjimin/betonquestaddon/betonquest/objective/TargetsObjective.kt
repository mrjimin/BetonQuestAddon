package kr.mrjimin.betonquestaddon.betonquest.objective

import kr.mrjimin.betonquestaddon.config.NotifyMessage
import org.betonquest.betonquest.api.instruction.Argument
import org.betonquest.betonquest.api.profile.OnlineProfile
import org.betonquest.betonquest.api.quest.objective.service.ObjectiveService

abstract class TargetsObjective(
    service: ObjectiveService,
    targetAmount: Argument<Number>,
    private val identifiers: Argument<List<String>>,
    private val targetIds: Argument<List<String>>,
    notifyMessage: NotifyMessage
) : BaseObjective(service, targetAmount, notifyMessage) {

    protected fun handle(profile: OnlineProfile, identifiersId: String, targetId: String) {
        process(profile, {
            matches(identifiers.getValue(profile), identifiersId) &&
                    matchesOrEmpty(targetIds.getValue(profile), targetId)
        })
    }

    private fun matchesOrEmpty(patterns: List<String>, value: String): Boolean {
        return patterns.isEmpty() || matches(patterns, value)
    }
}