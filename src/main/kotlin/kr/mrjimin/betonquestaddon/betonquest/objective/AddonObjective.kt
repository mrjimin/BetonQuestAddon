package kr.mrjimin.betonquestaddon.betonquest.objective

import kr.mrjimin.betonquestaddon.config.NotifyMessage
import kr.mrjimin.betonquestaddon.util.DefaultOptions
import org.betonquest.betonquest.api.CountingObjective
import org.betonquest.betonquest.api.profile.OnlineProfile
import org.betonquest.betonquest.api.quest.objective.service.ObjectiveService
import org.bukkit.Location
import org.bukkit.event.Cancellable

abstract class AddonObjective(
    service: ObjectiveService,
    private val options: DefaultOptions,
    notifyMessage: NotifyMessage,
) : CountingObjective(service, options.amount, notifyMessage.toKey()) {

    protected fun success(
        profile: OnlineProfile,
        matches: Boolean,
        cancellable: Cancellable,
        location: Location? = null,
        progress: Int = 1
    ) {
        if (!matches) return

        options.locationFilter?.let { filter ->
            val loc = location ?: return
            if (!filter.matches(profile, loc)) return
        }

        if (options.isCancelled.getValue(profile)) {
            cancellable.isCancelled = true
            return
        }

        getCountingData(profile)?.progress(progress)
        completeIfDoneOrNotify(profile)
    }
}