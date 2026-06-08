package kr.mrjimin.betonquestaddon.compatibility.customcrops.objective.wateringcan

import kr.mrjimin.betonquestaddon.betonquest.objective.AddonObjective
import kr.mrjimin.betonquestaddon.config.NotifyMessage
import kr.mrjimin.betonquestaddon.util.DefaultOptions
import kr.mrjimin.betonquestaddon.util.DualIdTarget
import net.momirealms.customcrops.api.event.WateringCanWaterSprinklerEvent
import org.betonquest.betonquest.api.instruction.Argument
import org.betonquest.betonquest.api.profile.OnlineProfile
import org.betonquest.betonquest.api.quest.objective.service.ObjectiveService

class CanSprinklerObjective(
    service: ObjectiveService,
    options: DefaultOptions,
    notifyMessage: NotifyMessage,
    private val id: Argument<List<String>>,
    private val sprinklerId: Argument<List<String>>? = null
) : AddonObjective(service, options, notifyMessage) {

    fun onWateringSprinkler(event: WateringCanWaterSprinklerEvent, profile: OnlineProfile) {
        handle(profile,
            DualIdTarget(
                event.wateringCanConfig().id(),
                event.sprinklerConfig().id()
            ),
            event
        )
    }

    private fun handle(profile: OnlineProfile, target: DualIdTarget, event: WateringCanWaterSprinklerEvent) {
        sprinklerId?.getValue(profile)?.equals(target.targetId)?.let { if (!it) return }
        success(profile, id.getValue(profile).equals(target.id), event, event.location())
    }

}