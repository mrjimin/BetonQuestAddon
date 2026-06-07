package kr.mrjimin.betonquestaddon.compatibility.customfishing.objective

import kr.mrjimin.betonquestaddon.config.NotifyMessage
import kr.mrjimin.betonquestaddon.util.DefaultOptions
import net.momirealms.customfishing.api.event.TotemActivateEvent
import org.betonquest.betonquest.api.CountingObjective
import org.betonquest.betonquest.api.instruction.Argument
import org.betonquest.betonquest.api.profile.OnlineProfile
import org.betonquest.betonquest.api.quest.objective.service.ObjectiveService

class ActivateTotemObjective(
    service: ObjectiveService,
    private val options: DefaultOptions,
    notifyMessage: NotifyMessage,
    private val id: Argument<List<String>>,
) : CountingObjective(service, options.amount, notifyMessage.toKey()) {

    fun onActivateTotem(event: TotemActivateEvent, profile: OnlineProfile) {
        handle(profile, event.config.id(), event)
    }

    fun handle(
        profile: OnlineProfile,
        target: String,
        event: TotemActivateEvent
    ) {
        options.locationFilter?.let { filter ->
            if (!filter.matches(profile, event.coreLocation)) return
        }

        if (options.isCancelled.getValue(profile)) {
            event.isCancelled = true
            return
        }

        if (id.getValue(profile).equals(target)) {
            getCountingData(profile)?.progress()
            completeIfDoneOrNotify(profile)
        }
    }
}