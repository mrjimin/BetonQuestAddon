package kr.mrjimin.betonquestaddon.compatibility.customcrops.objective.common

import kr.mrjimin.betonquestaddon.betonquest.objective.SimpleTargetsObjective
import kr.mrjimin.betonquestaddon.config.NotifyMessage
import net.momirealms.customcrops.api.event.ScarecrowBreakEvent
import net.momirealms.customcrops.api.event.ScarecrowPlaceEvent
import org.betonquest.betonquest.api.instruction.Argument
import org.betonquest.betonquest.api.profile.OnlineProfile
import org.betonquest.betonquest.api.quest.objective.service.ObjectiveService
import org.bukkit.entity.Player

class ScarecrowObjective(
    service: ObjectiveService,
    targetAmount: Argument<Number>,
    identifiers: Argument<List<String>>,
    notifyMessage: NotifyMessage
) : SimpleTargetsObjective(service, targetAmount, identifiers, notifyMessage) {

    fun onPlace(event: ScarecrowPlaceEvent, profile: OnlineProfile) {
        handle(profile, event.scarecrowItemID())
    }

    fun onBreak(event: ScarecrowBreakEvent, profile: OnlineProfile) {
        handle(profile, event.scarecrowItemID())
    }

}