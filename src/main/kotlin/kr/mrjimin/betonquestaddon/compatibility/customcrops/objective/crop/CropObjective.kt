package kr.mrjimin.betonquestaddon.compatibility.customcrops.objective.crop

import kr.mrjimin.betonquestaddon.betonquest.objective.AddonObjective
import kr.mrjimin.betonquestaddon.config.NotifyMessage
import kr.mrjimin.betonquestaddon.util.DefaultOptions
import net.momirealms.customcrops.api.event.CropBreakEvent
import net.momirealms.customcrops.api.event.CropInteractEvent
import net.momirealms.customcrops.api.event.CropPlantEvent
import org.betonquest.betonquest.api.instruction.Argument
import org.betonquest.betonquest.api.profile.OnlineProfile
import org.betonquest.betonquest.api.quest.objective.service.ObjectiveService
import org.bukkit.Location
import org.bukkit.event.Cancellable

class CropObjective(
    service: ObjectiveService,
    options: DefaultOptions,
    notifyMessage: NotifyMessage,
    private val id: Argument<List<String>>
) : AddonObjective(service, options, notifyMessage) {

    fun onPlace(event: CropPlantEvent, profile: OnlineProfile) {
        handle(profile, event.cropConfig().id(), event.location(), event)
    }

    fun onBreak(event: CropBreakEvent, profile: OnlineProfile) {
        handle(profile, event.cropStageItemID(), event.location(), event)
    }

    fun onInteract(event: CropInteractEvent, profile: OnlineProfile) {
        handle(profile, event.cropStageItemID(), event.location(), event)
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