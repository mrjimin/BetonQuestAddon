package kr.mrjimin.betonquestaddon.compatibility.customcrops.objective.crop

import kr.mrjimin.betonquestaddon.betonquest.objective.AddonObjective
import kr.mrjimin.betonquestaddon.config.NotifyMessage
import kr.mrjimin.betonquestaddon.util.ObjectiveOptions
import net.momirealms.customcrops.api.event.CropBreakEvent
import net.momirealms.customcrops.api.event.CropInteractEvent
import net.momirealms.customcrops.api.event.CropPlantEvent
import org.betonquest.betonquest.api.instruction.Argument
import org.betonquest.betonquest.api.profile.OnlineProfile
import org.betonquest.betonquest.api.quest.objective.service.ObjectiveService

class CropObjective(
    service: ObjectiveService,
    amount: Argument<Number>,
    options: ObjectiveOptions,
    notifyMessage: NotifyMessage
) : AddonObjective<String>(service, amount, options, notifyMessage) {

    fun onPlace(event: CropPlantEvent, profile: OnlineProfile) {
        handle(profile, event.cropConfig().id(), event)
    }

    fun onBreak(event: CropBreakEvent, profile: OnlineProfile) {
        handle(profile, event.cropStageItemID(), event)
    }

    fun onInteract(event: CropInteractEvent, profile: OnlineProfile) {
        handle(profile, event.cropStageItemID(), event)
    }

}