package kr.mrjimin.betonquestaddon.compatibility.customcrops.objective.sprinkler

import kr.mrjimin.betonquestaddon.config.NotifyMessage
import kr.mrjimin.betonquestaddon.util.DefaultOptions
import net.momirealms.customcrops.api.event.SprinklerBreakEvent
import net.momirealms.customcrops.api.event.SprinklerInteractEvent
import net.momirealms.customcrops.api.event.SprinklerPlaceEvent
import org.betonquest.betonquest.api.CountingObjective
import org.betonquest.betonquest.api.instruction.Argument
import org.betonquest.betonquest.api.profile.OnlineProfile
import org.betonquest.betonquest.api.quest.objective.service.ObjectiveService
import org.bukkit.Location
import org.bukkit.event.Cancellable

class SprinklerObjective(
    service: ObjectiveService,
    private val options: DefaultOptions,
    notifyMessage: NotifyMessage,
    private val id: Argument<List<String>>,
) : CountingObjective(service, options.amount, notifyMessage.toKey()) {

    fun onPlace(event: SprinklerPlaceEvent, profile: OnlineProfile) {
        handle(profile, event.sprinklerConfig().id(), event.location(), event)
    }

    fun onBreak(event: SprinklerBreakEvent, profile: OnlineProfile) {
        handle(profile, event.sprinklerConfig().id(), event.location(), event)
    }

    fun onInteract(event: SprinklerInteractEvent, profile: OnlineProfile) {
        handle(profile, event.sprinklerConfig().id(), event.location(), event)
    }

    fun handle(
        profile: OnlineProfile,
        target: String,
        targetLocation: Location,
        event: Cancellable
    ) {
        options.locationFilter?.let { filter ->
            if (!filter.matches(profile, targetLocation)) return
        }

        if (options.isCancelled.getValue(profile)) {
            event.isCancelled = true
            return
        }

        if (id.getValue(profile).equals(target)) {
            getCountingData(profile)?.progress()
            completeIfDoneOrNotify(profile)
        }
    }

}