package kr.mrjimin.betonquestaddon.compatibility.customfishing.objective

import kr.mrjimin.betonquestaddon.config.NotifyMessage
import kr.mrjimin.betonquestaddon.util.DefaultOptions
import net.momirealms.customfishing.api.event.FishingResultEvent
import org.betonquest.betonquest.api.CountingObjective
import org.betonquest.betonquest.api.instruction.Argument
import org.betonquest.betonquest.api.profile.OnlineProfile
import org.betonquest.betonquest.api.quest.objective.service.ObjectiveService

class CaughtFishObjective(
    service: ObjectiveService,
    private val options: DefaultOptions,
    notifyMessage: NotifyMessage,
    private val id: Argument<List<String>>
) : CountingObjective(service, options.amount, notifyMessage.toKey()) {

    fun onFish(event: FishingResultEvent, profile: OnlineProfile) {
        if (event.result == FishingResultEvent.Result.FAILURE) return
        handle(profile, event.loot.id(), event)
    }

    fun onFishGroup(event: FishingResultEvent, profile: OnlineProfile) {
        if (event.result == FishingResultEvent.Result.FAILURE) return
        val groups = event.loot.lootGroup()
        for (group in groups) {
            handle(profile, group, event)
        }
    }

    fun handle(
        profile: OnlineProfile,
        target: String,
        event: FishingResultEvent
    ) {
        options.locationFilter?.let { filter ->
            if (!filter.matches(profile, event.fishHook.location)) return
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