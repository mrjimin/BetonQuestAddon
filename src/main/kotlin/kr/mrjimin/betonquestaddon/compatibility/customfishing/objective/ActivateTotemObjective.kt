package kr.mrjimin.betonquestaddon.compatibility.customfishing.objective

import kr.mrjimin.betonquestaddon.betonquest.objective.AddonObjective
import kr.mrjimin.betonquestaddon.config.NotifyMessage
import kr.mrjimin.betonquestaddon.util.ObjectiveOptions
import net.momirealms.customfishing.api.event.TotemActivateEvent
import org.betonquest.betonquest.api.instruction.Argument
import org.betonquest.betonquest.api.profile.OnlineProfile
import org.betonquest.betonquest.api.quest.objective.service.ObjectiveService

class ActivateTotemObjective(
    service: ObjectiveService,
    amount: Argument<Number>,
    options: ObjectiveOptions,
) : AddonObjective<String>(service, amount, options, NotifyMessage.CUSTOM_FISHING_ACTIVATE_TOTEM) {

    fun onActivateTotem(event: TotemActivateEvent, profile: OnlineProfile) {
        handle(profile, event.config.id(), event)
        event.player.sendMessage(event.config.id())
    }
}