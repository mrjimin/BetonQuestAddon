package kr.mrjimin.betonquestaddon.betonquest.objective.smith

import kr.mrjimin.betonquestaddon.betonquest.objective.AddonObjective
import kr.mrjimin.betonquestaddon.config.NotifyMessage
import kr.mrjimin.betonquestaddon.util.DefaultOptions
import org.betonquest.betonquest.api.instruction.Argument
import org.betonquest.betonquest.api.instruction.type.ItemWrapper
import org.betonquest.betonquest.api.profile.OnlineProfile
import org.betonquest.betonquest.api.quest.objective.service.ObjectiveService
import org.bukkit.event.inventory.SmithItemEvent
import org.bukkit.inventory.SmithingInventory

class SmithObjective(
    service: ObjectiveService,
    options: DefaultOptions,
    private val item: Argument<ItemWrapper>
) : AddonObjective(service, options, NotifyMessage.BQA_SMITH) {

    fun onSmith(event: SmithItemEvent, profile: OnlineProfile) {
        val result = (event.view.topInventory as? SmithingInventory)?.result ?: return
        if (result.type.isAir) return

        val matches = item.getValue(profile).matches(result, profile)
        success(profile, matches, event, null, result.amount)
    }
}