package kr.mrjimin.betonquestaddon.compatibility.customcrops.objective.sprinkler

import kr.mrjimin.betonquestaddon.betonquest.objective.AddonObjective
import kr.mrjimin.betonquestaddon.config.NotifyMessage
import kr.mrjimin.betonquestaddon.util.ObjectiveOptions
import net.momirealms.customcrops.api.event.SprinklerBreakEvent
import net.momirealms.customcrops.api.event.SprinklerInteractEvent
import net.momirealms.customcrops.api.event.SprinklerPlaceEvent
import org.betonquest.betonquest.api.instruction.Argument
import org.betonquest.betonquest.api.profile.OnlineProfile
import org.betonquest.betonquest.api.quest.objective.service.ObjectiveService

class SprinklerObjective(
    service: ObjectiveService,
    amount: Argument<Number>,
    options: ObjectiveOptions,
    notifyMessage: NotifyMessage
) : AddonObjective<String>(service, amount, options, notifyMessage) {

    fun onPlace(event: SprinklerPlaceEvent, profile: OnlineProfile) {
        handle(profile, event.sprinklerConfig().id(), event)
    }

    fun onBreak(event: SprinklerBreakEvent, profile: OnlineProfile) {
        handle(profile, event.sprinklerConfig().id(), event)
    }

    fun onInteract(event: SprinklerInteractEvent, profile: OnlineProfile) {
        handle(profile, event.sprinklerConfig().id(), event)
    }

}