package kr.mrjimin.betonquestaddon.compatibility.customcrops.objective.wateringcan

import kr.mrjimin.betonquestaddon.betonquest.objective.AddonObjective
import kr.mrjimin.betonquestaddon.config.NotifyMessage
import kr.mrjimin.betonquestaddon.util.DualIdTarget
import kr.mrjimin.betonquestaddon.util.ObjectiveOptions
import net.momirealms.customcrops.api.event.WateringCanWaterSprinklerEvent
import org.betonquest.betonquest.api.instruction.Argument
import org.betonquest.betonquest.api.profile.OnlineProfile
import org.betonquest.betonquest.api.quest.objective.service.ObjectiveService

class CanSprinklerObjective(
    service: ObjectiveService,
    amount: Argument<Number>,
    options: ObjectiveOptions
) : AddonObjective<DualIdTarget>(service, amount, options, NotifyMessage.CUSTOM_CROPS_CAN_SPRINKLER) {

    fun onWateringSprinkler(event: WateringCanWaterSprinklerEvent, profile: OnlineProfile) {
        handle(profile,
            DualIdTarget(
                event.wateringCanConfig().id(),
                event.sprinklerConfig().id()
            ), event)
    }

    override fun getId(target: DualIdTarget): String = target.id

    override fun getTargetId(target: DualIdTarget): String = target.targetId

}