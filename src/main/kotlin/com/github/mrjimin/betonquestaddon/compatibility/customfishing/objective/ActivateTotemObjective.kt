package com.github.mrjimin.betonquestaddon.compatibility.customfishing.objective

import com.github.mrjimin.betonquestaddon.betonquest.objective.SimpleTargetsObjective
import com.github.mrjimin.betonquestaddon.config.NotifyMessage
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