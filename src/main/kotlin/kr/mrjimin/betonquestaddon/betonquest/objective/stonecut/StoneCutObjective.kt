package kr.mrjimin.betonquestaddon.betonquest.objective.stonecut

import kr.mrjimin.betonquestaddon.betonquest.objective.AddonObjective
import kr.mrjimin.betonquestaddon.config.NotifyMessage
import kr.mrjimin.betonquestaddon.util.DefaultOptions
import org.betonquest.betonquest.api.instruction.Argument
import org.betonquest.betonquest.api.instruction.type.ItemWrapper
import org.betonquest.betonquest.api.profile.OnlineProfile
import org.betonquest.betonquest.api.quest.objective.service.ObjectiveService
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.inventory.StonecutterInventory

class StoneCutObjective(
    service: ObjectiveService,
    options: DefaultOptions,
    private val item: Argument<ItemWrapper>
) : AddonObjective(service, options, NotifyMessage.BQA_STONE_CUT) {

    fun onStonCutting(event: InventoryClickEvent, profile: OnlineProfile) {
        val result = (event.view.topInventory as? StonecutterInventory)?.result ?: return
        if (result.type.isAir) return

        val matches = item.getValue(profile).matches(result, profile)
        success(profile, matches, event, null, result.amount)
    }
}