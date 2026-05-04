package kr.mrjimin.betonquestaddon.betonquest.objective

import kr.mrjimin.betonquestaddon.config.NotifyMessage
import kr.mrjimin.betonquestaddon.util.ObjectiveOptions
import org.betonquest.betonquest.api.instruction.Argument
import org.betonquest.betonquest.api.profile.OnlineProfile
import org.betonquest.betonquest.api.profile.Profile
import org.betonquest.betonquest.api.quest.objective.service.ObjectiveService
import org.bukkit.Location
import org.bukkit.event.Cancellable

abstract class AddonObjective<T>(
    service: ObjectiveService,
    amount: Argument<Number>,
    private val options: ObjectiveOptions,
    notifyMessage: NotifyMessage
) : AbstractObjective(service, amount, notifyMessage) {

    protected open fun getId(target: T): String? = when (target) {
        is String -> target
        else -> null
    }

    protected open fun getTargetId(target: T): String? = null
    protected open fun getLocation(target: T): Location? = null

    protected open fun matchesTarget(profile: OnlineProfile, target: T): Boolean = true

//    protected fun handle(profile: OnlineProfile, target: T, event: Cancellable? = null) {
//        process(profile, {
//            options.ids.matches(profile, getId(target)) &&
//                    options.targetIds.matches(profile, getTargetId(target)) &&
//                    options.locationFilter.matches(profile, getLocation(target))
//        }) {
//            if (event != null && options.isCancelled?.getValue(profile) == true) {
//                event.isCancelled = true
//            }
//        }
//    }
    protected fun handle(profile: OnlineProfile, target: T, event: Cancellable? = null) {
        process(profile, {
            match(options.ids, profile, getId(target)) &&
                    match(options.targetIds, profile, getTargetId(target)) &&
                    matchesTarget(profile, target) &&
                    (options.locationFilter?.matches(profile, getLocation(target)) ?: true)
        }) {
            if (event != null && options.isCancelled?.getValue(profile) == true) {
                event.isCancelled = true
            }
        }
    }

    private fun Argument<List<String>>?.matches(profile: OnlineProfile, value: String?): Boolean {
        if (this == null) return true
        if (value == null) return false
        return matches(getValue(profile), value)
    }

    private fun LocationFilter?.matches(profile: Profile, loc: Location?): Boolean {
        if (this == null || loc == null) return true
        return this.matches(profile, loc)
    }

    private fun match(
        arg: Argument<List<String>>?,
        profile: OnlineProfile,
        value: String?
    ): Boolean {
        if (arg == null) return true
        if (value == null) return false
        return matches(arg.getValue(profile), value)
    }
}