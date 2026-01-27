package kr.mrjimin.betonquestaddon.compatibility.customfishing.objective

import kr.mrjimin.betonquestaddon.betonquest.objective.SimpleTargetsObjective
import kr.mrjimin.betonquestaddon.config.NotifyMessage
import net.momirealms.customfishing.api.event.TotemActivateEvent
import org.betonquest.betonquest.api.instruction.Argument
import org.betonquest.betonquest.api.quest.objective.service.ObjectiveService

class ActivateTotemObjective(
    service: ObjectiveService,
    targetAmount: Argument<Number>,
    identifiers: Argument<List<String>>
) : SimpleTargetsObjective(service, targetAmount, identifiers, NotifyMessage.CUSTOM_FISHING_ACTIVATE_TOTEM) {

    fun onActivateTotem(event: TotemActivateEvent) {
        wildcardHandle(event.player, event.config.id())
    }
}