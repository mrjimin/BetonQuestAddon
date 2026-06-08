package kr.mrjimin.betonquestaddon.compatibility.customcrops.objective.common

import kr.mrjimin.betonquestaddon.betonquest.objective.AddonObjective
import kr.mrjimin.betonquestaddon.config.NotifyMessage
import kr.mrjimin.betonquestaddon.util.DefaultOptions
import net.momirealms.customcrops.api.event.ScarecrowBreakEvent
import net.momirealms.customcrops.api.event.ScarecrowInteractEvent
import net.momirealms.customcrops.api.event.ScarecrowPlaceEvent
import org.betonquest.betonquest.api.instruction.Argument
import org.betonquest.betonquest.api.profile.OnlineProfile
import org.betonquest.betonquest.api.quest.objective.service.ObjectiveService
import org.bukkit.Location
import org.bukkit.event.Cancellable

class ScarecrowObjective(
    service: ObjectiveService,
    options: DefaultOptions,
    notifyMessage: NotifyMessage,
    private val id: Argument<List<String>>
) : AddonObjective(service, options, notifyMessage) {


    fun onPlace(event: ScarecrowPlaceEvent, profile: OnlineProfile) {
        handle(profile, event.scarecrowItemID(), event.location(), event)
    }

    fun onBreak(event: ScarecrowBreakEvent, profile: OnlineProfile) {
        handle(profile, event.scarecrowItemID(), event.location(), event)
    }

    fun onInteract(event: ScarecrowInteractEvent, profile: OnlineProfile) {
        handle(profile, event.scarecrowItemID(), event.location(), event)
    }

    private fun handle(
        profile: OnlineProfile,
        target: String,
        targetLocation: Location,
        event: Cancellable
    ) {
        success(profile, id.getValue(profile).equals(target), event, targetLocation)
    }

}