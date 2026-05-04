package kr.mrjimin.betonquestaddon.compatibility.customcrops.objective.wateringcan

import kr.mrjimin.betonquestaddon.betonquest.objective.AddonObjective
import kr.mrjimin.betonquestaddon.config.NotifyMessage
import kr.mrjimin.betonquestaddon.util.ObjectiveOptions
import net.momirealms.customcrops.api.event.WateringCanFillEvent
import org.betonquest.betonquest.api.instruction.Argument
import org.betonquest.betonquest.api.profile.OnlineProfile
import org.betonquest.betonquest.api.quest.objective.service.ObjectiveService

class CanFillObjective(
    service: ObjectiveService,
    amount: Argument<Number>,
    options: ObjectiveOptions
) : AddonObjective<String>(service, amount, options, NotifyMessage.CUSTOM_CROPS_CAN_FILL) {

    fun onFillWateringCan(event: WateringCanFillEvent, profile: OnlineProfile) {
        handle(profile, event.wateringCanConfig().id(), event)
    }

}