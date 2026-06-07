package kr.mrjimin.betonquestaddon.betonquest.objective

import kr.mrjimin.betonquestaddon.config.NotifyMessage
import kr.mrjimin.betonquestaddon.util.DefaultOptions
import org.betonquest.betonquest.api.CountingObjective
import org.betonquest.betonquest.api.instruction.Argument
import org.betonquest.betonquest.api.instruction.type.ItemWrapper
import org.betonquest.betonquest.api.profile.OnlineProfile
import org.betonquest.betonquest.api.quest.objective.service.ObjectiveService
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.event.inventory.InventoryType
import org.bukkit.inventory.StonecutterInventory

class StoneCutObjective(
    service: ObjectiveService,
    options: DefaultOptions,
    private val item: Argument<ItemWrapper>
) : CountingObjective(service, options.amount, NotifyMessage.BQA_STONE_CUT.toKey()) {

    fun onStonCutting(event: InventoryClickEvent, profile: OnlineProfile) {
        val top = event.view.topInventory
        if (top.type != InventoryType.STONECUTTER) return
        if (event.slotType != InventoryType.SlotType.RESULT) return

        val inv = top as? StonecutterInventory ?: return
        val result = inv.result ?: return
        if (result.type.isAir) return

        if (item.getValue(profile).matches(result, profile)) {
            getCountingData(profile)?.progress(result.amount)
            completeIfDoneOrNotify(profile)
        }
    }
}