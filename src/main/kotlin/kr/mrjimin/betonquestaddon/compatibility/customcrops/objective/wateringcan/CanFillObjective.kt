package kr.mrjimin.betonquestaddon.compatibility.customcrops.objective.wateringcan

import kr.mrjimin.betonquestaddon.betonquest.objective.SimpleTargetsObjective
import kr.mrjimin.betonquestaddon.config.NotifyMessage
import net.momirealms.customcrops.api.event.WateringCanFillEvent
import org.betonquest.betonquest.api.instruction.Argument
import org.betonquest.betonquest.api.quest.objective.service.ObjectiveService

class CanFillObjective(
    service: ObjectiveService,
    targetAmount: Argument<Number>,
    identifiers: Argument<List<String>>
) : SimpleTargetsObjective(service, targetAmount, identifiers, NotifyMessage.CUSTOM_CROPS_CAN_FILL) {

    fun onFillWateringCan(event: WateringCanFillEvent) {
        handle(event.player, event.wateringCanConfig().id())
    }
}