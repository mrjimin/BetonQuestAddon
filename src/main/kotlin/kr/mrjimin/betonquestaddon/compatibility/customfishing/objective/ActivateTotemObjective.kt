package kr.mrjimin.betonquestaddon.compatibility.customfishing.objective

import kr.mrjimin.betonquestaddon.betonquest.objective.AddonObjective
import kr.mrjimin.betonquestaddon.config.NotifyMessage
import kr.mrjimin.betonquestaddon.util.DefaultOptions
import net.momirealms.customfishing.api.event.TotemActivateEvent
import org.betonquest.betonquest.api.instruction.Argument
import org.betonquest.betonquest.api.profile.OnlineProfile
import org.betonquest.betonquest.api.quest.objective.service.ObjectiveService

class ActivateTotemObjective(
    service: ObjectiveService,
    options: DefaultOptions,
    notifyMessage: NotifyMessage,
    private val id: Argument<List<String>>,
) : AddonObjective(service, options, notifyMessage) {

    fun onActivateTotem(event: TotemActivateEvent, profile: OnlineProfile) {
        handle(profile, event.config.id(), event)
    }

    private fun handle(
        profile: OnlineProfile,
        target: String,
        event: TotemActivateEvent
    ) {
        success(profile, id.getValue(profile).contains(target), event, event.coreLocation)
    }
}