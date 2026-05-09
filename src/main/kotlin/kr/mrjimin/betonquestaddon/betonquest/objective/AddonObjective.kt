package kr.mrjimin.betonquestaddon.betonquest.objective

import kr.mrjimin.betonquestaddon.config.NotifyMessage
import kr.mrjimin.betonquestaddon.util.ObjectiveOptions
import org.betonquest.betonquest.api.instruction.Argument
import org.betonquest.betonquest.api.profile.OnlineProfile
import org.betonquest.betonquest.api.quest.objective.service.ObjectiveService
import org.bukkit.Location
import org.bukkit.event.Cancellable

abstract class AddonObjective<T>(
    service: ObjectiveService,
    amount: Argument<Number>,
    private val options: ObjectiveOptions,
    notifyMessage: NotifyMessage
) : AbstractObjective(service, amount, notifyMessage) {

    protected open fun getId(target: T): String? = (target as? String)
    protected open fun getTargetId(target: T): String? = null
    protected open fun getLocation(target: T): Location? = null
    protected open fun matchesTarget(profile: OnlineProfile, target: T): Boolean = true

    protected fun handle(profile: OnlineProfile, target: T, event: Cancellable? = null) {
        process(profile, {
            if (!match(options.ids, profile, getId(target))) return@process false
            if (!match(options.targetIds, profile, getTargetId(target))) return@process false
            if (!matchesTarget(profile, target)) return@process false

            val filter = options.locationFilter
            if (filter != null) {
                val loc = getLocation(target) ?: return@process false
                if (!filter.matches(profile, loc)) return@process false
            }

            true
        }) {
            if (event != null && options.isCancelled?.getValue(profile) == true) {
                event.isCancelled = true
            }
        }
    }

    private fun match(
        arg: Argument<List<String>>?,
        profile: OnlineProfile,
        value: String?
    ): Boolean {
        val targetList = arg?.getValue(profile) ?: return true
        if (targetList.isEmpty()) return true

        if (value == null) return false

        return targetList.contains(value)
    }
}