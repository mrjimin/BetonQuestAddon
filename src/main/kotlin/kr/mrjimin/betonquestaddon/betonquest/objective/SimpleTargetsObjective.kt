package kr.mrjimin.betonquestaddon.betonquest.objective

import kr.mrjimin.betonquestaddon.config.NotifyMessage
import kr.mrjimin.betonquestaddon.util.matcher.WildcardPatternMatcher
import org.betonquest.betonquest.api.CountingObjective
import org.betonquest.betonquest.api.QuestException
import org.betonquest.betonquest.api.instruction.Argument
import org.betonquest.betonquest.api.quest.objective.service.ObjectiveService
import org.bukkit.entity.Player

abstract class SimpleTargetsObjective(
    service: ObjectiveService,
    targetAmount: Argument<Number>,
    private val identifiers: Argument<List<String>>,
    notifyMessage: NotifyMessage
) : CountingObjective(service, targetAmount, notifyMessage.toKey()) {

    @Throws(QuestException::class)
    protected fun handle(player: Player, id: String) {
        val profile = service.profileProvider.getProfile(player) ?: return
        if (!service.containsProfile(profile) || !service.checkConditions(profile)) return

        if (identifiers.getValue(profile).contains(id)) {
            getCountingData(profile)?.progress()
            completeIfDoneOrNotify(profile)
        }
    }

    private val matcherCache = mutableMapOf<List<String>, WildcardPatternMatcher>()

    @Throws(QuestException::class)
    protected fun wildcardHandle(player: Player, id: String) {
        val profile = service.profileProvider.getProfile(player) ?: return
        if (!service.containsProfile(profile) || !service.checkConditions(profile)) return

        val patterns = identifiers.getValue(profile)
        if (getMatcher(patterns).matches(id)) {
            getCountingData(profile)?.progress()
            completeIfDoneOrNotify(profile)
        }
    }

    private fun getMatcher(patterns: List<String>): WildcardPatternMatcher {
        return matcherCache.getOrPut(patterns) { WildcardPatternMatcher(patterns) }
    }
}