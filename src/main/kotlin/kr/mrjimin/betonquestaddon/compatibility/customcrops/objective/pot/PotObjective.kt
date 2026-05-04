package kr.mrjimin.betonquestaddon.compatibility.customcrops.objective.pot

import kr.mrjimin.betonquestaddon.betonquest.objective.AddonObjective
import kr.mrjimin.betonquestaddon.config.NotifyMessage
import kr.mrjimin.betonquestaddon.util.ObjectiveOptions
import net.momirealms.customcrops.api.event.PotBreakEvent
import net.momirealms.customcrops.api.event.PotInteractEvent
import net.momirealms.customcrops.api.event.PotPlaceEvent
import org.betonquest.betonquest.api.instruction.Argument
import org.betonquest.betonquest.api.profile.OnlineProfile
import org.betonquest.betonquest.api.quest.objective.service.ObjectiveService

class PotObjective(
    service: ObjectiveService,
    amount: Argument<Number>,
    options: ObjectiveOptions,
    notifyMessage: NotifyMessage
) : AddonObjective<String>(service, amount, options, notifyMessage) {

    fun onPlace(event: PotPlaceEvent, profile: OnlineProfile) {
        handle(profile, event.potConfig().id(), event)
    }

    fun onBreak(event: PotBreakEvent, profile: OnlineProfile) {
        handle(profile, event.potConfig().id(), event)
    }

    fun onInteract(event: PotInteractEvent, profile: OnlineProfile) {
        handle(profile, event.potConfig().id(), event)
    }

}