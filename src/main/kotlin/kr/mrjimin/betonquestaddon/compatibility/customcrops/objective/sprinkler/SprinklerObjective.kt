package kr.mrjimin.betonquestaddon.compatibility.customcrops.objective.sprinkler

import kr.mrjimin.betonquestaddon.betonquest.objective.AddonObjective
import kr.mrjimin.betonquestaddon.config.NotifyMessage
import kr.mrjimin.betonquestaddon.util.DefaultOptions
import net.momirealms.customcrops.api.event.SprinklerBreakEvent
import net.momirealms.customcrops.api.event.SprinklerInteractEvent
import net.momirealms.customcrops.api.event.SprinklerPlaceEvent
import org.betonquest.betonquest.api.instruction.Argument
import org.betonquest.betonquest.api.profile.OnlineProfile
import org.betonquest.betonquest.api.quest.objective.service.ObjectiveService
import org.bukkit.Location
import org.bukkit.event.Cancellable

class SprinklerObjective(
    service: ObjectiveService,
    options: DefaultOptions,
    notifyMessage: NotifyMessage,
    private val id: Argument<List<String>>,
) : AddonObjective(service, options, notifyMessage) {

    fun onPlace(event: SprinklerPlaceEvent, profile: OnlineProfile) {
        handle(profile, event.sprinklerConfig().id(), event.location(), event)
    }

    fun onBreak(event: SprinklerBreakEvent, profile: OnlineProfile) {
        handle(profile, event.sprinklerConfig().id(), event.location(), event)
    }

    fun onInteract(event: SprinklerInteractEvent, profile: OnlineProfile) {
        handle(profile, event.sprinklerConfig().id(), event.location(), event)
    }

    private fun handle(
        profile: OnlineProfile,
        target: String,
        targetLocation: Location,
        event: Cancellable
    ) {
        success(profile, id.getValue(profile).contains(target), event, targetLocation)
    }

}