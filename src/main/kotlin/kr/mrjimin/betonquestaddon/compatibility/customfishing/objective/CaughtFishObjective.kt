package kr.mrjimin.betonquestaddon.compatibility.customfishing.objective

import kr.mrjimin.betonquestaddon.betonquest.objective.AddonObjective
import kr.mrjimin.betonquestaddon.config.NotifyMessage
import kr.mrjimin.betonquestaddon.util.ObjectiveOptions
import net.momirealms.customfishing.api.event.FishingResultEvent
import org.betonquest.betonquest.api.instruction.Argument
import org.betonquest.betonquest.api.profile.OnlineProfile
import org.betonquest.betonquest.api.quest.objective.service.ObjectiveService

class CaughtFishObjective(
    service: ObjectiveService,
    amount: Argument<Number>,
    options: ObjectiveOptions,
    notifyMessage: NotifyMessage
) : AddonObjective<String>(service, amount, options, notifyMessage) {

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
}