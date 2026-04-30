package kr.mrjimin.betonquestaddon.betonquest.objective

import kr.mrjimin.betonquestaddon.config.NotifyMessage
import org.betonquest.betonquest.api.instruction.Argument
import org.betonquest.betonquest.api.profile.OnlineProfile
import org.betonquest.betonquest.api.quest.objective.service.ObjectiveService

abstract class SimpleTargetsObjective(
    service: ObjectiveService,
    targetAmount: Argument<Number>,
    private val identifiers: Argument<List<String>>,
    notifyMessage: NotifyMessage
) : BaseObjective(service, targetAmount, notifyMessage) {

    protected fun handle(profile: OnlineProfile, id: String) {
        process(profile, {
            matches(identifiers.getValue(profile), id)
        })
    }
}