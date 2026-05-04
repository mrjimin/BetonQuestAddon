package kr.mrjimin.betonquestaddon.compatibility.customcrops.objective.common

import kr.mrjimin.betonquestaddon.betonquest.objective.AddonObjective
import kr.mrjimin.betonquestaddon.config.NotifyMessage
import kr.mrjimin.betonquestaddon.util.ObjectiveOptions
import net.momirealms.customcrops.api.event.ScarecrowBreakEvent
import net.momirealms.customcrops.api.event.ScarecrowInteractEvent
import net.momirealms.customcrops.api.event.ScarecrowPlaceEvent
import org.betonquest.betonquest.api.instruction.Argument
import org.betonquest.betonquest.api.profile.OnlineProfile
import org.betonquest.betonquest.api.quest.objective.service.ObjectiveService

class ScarecrowObjective(
    service: ObjectiveService,
    amount: Argument<Number>,
    options: ObjectiveOptions,
    notifyMessage: NotifyMessage
) : AddonObjective<String>(service, amount, options, notifyMessage) {

    fun onPlace(event: ScarecrowPlaceEvent, profile: OnlineProfile) {
        handle(profile, event.scarecrowItemID(), event)
    }

    fun onBreak(event: ScarecrowBreakEvent, profile: OnlineProfile) {
        handle(profile, event.scarecrowItemID(), event)
    }

    fun onInteract(event: ScarecrowInteractEvent, profile: OnlineProfile) {
        handle(profile, event.scarecrowItemID(), event)
    }

}