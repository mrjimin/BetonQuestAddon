package kr.mrjimin.betonquestaddon.compatibility.customcrops.objective.wateringcan

import kr.mrjimin.betonquestaddon.betonquest.objective.AddonObjective
import kr.mrjimin.betonquestaddon.config.NotifyMessage
import kr.mrjimin.betonquestaddon.util.DefaultOptions
import net.momirealms.customcrops.api.event.WateringCanFillEvent
import org.betonquest.betonquest.api.instruction.Argument
import org.betonquest.betonquest.api.profile.OnlineProfile
import org.betonquest.betonquest.api.quest.objective.service.ObjectiveService
import org.bukkit.Location
import org.bukkit.event.Cancellable

class CanFillObjective(
    service: ObjectiveService,
    options: DefaultOptions,
    notifyMessage: NotifyMessage,
    private val id: Argument<List<String>>,
) : AddonObjective(service, options, notifyMessage) {

    fun onFillWateringCan(event: WateringCanFillEvent, profile: OnlineProfile) {
        handle(profile, event.wateringCanConfig().id(), event.location(), event)
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