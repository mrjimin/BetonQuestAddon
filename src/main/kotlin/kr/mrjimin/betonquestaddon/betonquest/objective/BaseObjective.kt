package kr.mrjimin.betonquestaddon.betonquest.objective

import kr.mrjimin.betonquestaddon.config.NotifyMessage
import kr.mrjimin.betonquestaddon.util.matcher.WildcardPatternMatcher
import org.betonquest.betonquest.api.CountingObjective
import org.betonquest.betonquest.api.instruction.Argument
import org.betonquest.betonquest.api.profile.OnlineProfile
import org.betonquest.betonquest.api.quest.objective.service.ObjectiveService

abstract class BaseObjective(
    service: ObjectiveService,
    targetAmount: Argument<Number>,
    notifyMessage: NotifyMessage
) : CountingObjective(service, targetAmount, notifyMessage.toKey()) {

    private val matcherCache = mutableMapOf<List<String>, WildcardPatternMatcher>()

    protected fun process(
        profile: OnlineProfile,
        condition: () -> Boolean,
        onSuccess: () -> Unit = {}
    ) {
        if (!service.containsProfile(profile) || !service.checkConditions(profile)) return
        if (!condition()) return

        getCountingData(profile)?.progress()
        completeIfDoneOrNotify(profile)
        onSuccess()
    }

    protected fun matches(patterns: List<String>, value: String?): Boolean {
        if (value == null) return false

        return if (patterns.any { it.contains("*") }) {
            matcherCache
                .getOrPut(patterns) { WildcardPatternMatcher(patterns) }
                .matches(value)
        } else {
            patterns.contains(value)
        }
    }
}