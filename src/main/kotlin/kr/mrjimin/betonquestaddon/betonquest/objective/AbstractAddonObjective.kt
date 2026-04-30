package kr.mrjimin.betonquestaddon.betonquest.objective

import kr.mrjimin.betonquestaddon.config.NotifyMessage
import org.betonquest.betonquest.api.instruction.Argument
import org.betonquest.betonquest.api.profile.OnlineProfile
import org.betonquest.betonquest.api.profile.Profile
import org.betonquest.betonquest.api.quest.objective.service.ObjectiveService
import org.bukkit.Location
import org.bukkit.event.Cancellable

abstract class AbstractAddonObjective<T>(
    service: ObjectiveService,
    targetAmount: Argument<Number>,
    private val identifiers: Argument<List<String>>,
    private val isCancelled: Argument<Boolean>,
    private val location: Argument<Location>?,
    private val range: Argument<Number>,
    notifyMessage: NotifyMessage
) : BaseObjective(service, targetAmount, notifyMessage) {

    protected abstract fun getId(target: T): String?
    protected abstract fun getLocation(target: T): Location

    protected fun handle(profile: OnlineProfile, target: T, event: Cancellable) {
        process(profile, {
            matches(identifiers.getValue(profile), getId(target)) &&
                    isValidLocation(profile, getLocation(target))
        }) {
            if (isCancelled.getValue(profile)) {
                event.isCancelled = true
            }
        }
    }

    private fun isValidLocation(profile: Profile, targetLocation: Location): Boolean {
        val loc = location?.getValue(profile) ?: return true
        val rangeValue = range.getValue(profile).toDouble()

        return loc.world == targetLocation.world &&
                loc.distanceSquared(targetLocation) <= rangeValue * rangeValue
    }
}