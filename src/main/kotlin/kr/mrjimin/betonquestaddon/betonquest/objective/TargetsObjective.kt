package kr.mrjimin.betonquestaddon.betonquest.objective

import kr.mrjimin.betonquestaddon.config.NotifyMessage
import kr.mrjimin.betonquestaddon.util.matcher.WildcardPatternMatcher
import org.betonquest.betonquest.api.CountingObjective
import org.betonquest.betonquest.api.QuestException
import org.betonquest.betonquest.api.instruction.Argument
import org.betonquest.betonquest.api.quest.objective.service.ObjectiveService
import org.bukkit.entity.Player

abstract class TargetsObjective(
    service: ObjectiveService,
    targetAmount: Argument<Number>,
    private val identifiers: Argument<List<String>>,
    private val targetIds: Argument<List<String>>,
    notifyMessage: NotifyMessage
) : CountingObjective(service, targetAmount, notifyMessage.toKey()) {

    @Throws(QuestException::class)
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

    private val matcherCache = mutableMapOf<List<String>, WildcardPatternMatcher>()

    @Throws(QuestException::class)
    protected fun wildcardHandle(player: Player, identifiersId: String, targetId: String) {
        val profile = service.profileProvider.getProfile(player) ?: return
        if (!service.containsProfile(profile) || !service.checkConditions(profile)) return

        if (!getMatcher(identifiers.getValue(profile)).matches(identifiersId)) return

        val targetPatterns = targetIds.getValue(profile)
        if (targetPatterns.isEmpty() || getMatcher(targetPatterns).matches(targetId)) {
            getCountingData(profile)?.progress()
            completeIfDoneOrNotify(profile)
        }
    }

    private fun getMatcher(patterns: List<String>): WildcardPatternMatcher {
        return matcherCache.getOrPut(patterns) { WildcardPatternMatcher(patterns) }
    }
}