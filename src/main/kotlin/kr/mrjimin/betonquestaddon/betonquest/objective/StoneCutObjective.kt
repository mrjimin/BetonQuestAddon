package kr.mrjimin.betonquestaddon.betonquest.objective

import kr.mrjimin.betonquestaddon.config.NotifyMessage
import kr.mrjimin.betonquestaddon.util.ObjectiveOptions
import org.betonquest.betonquest.api.instruction.Argument
import org.betonquest.betonquest.api.profile.OnlineProfile
import org.betonquest.betonquest.api.quest.objective.service.ObjectiveService
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.event.inventory.InventoryType
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.StonecutterInventory

class StoneCutObjective(
    service: ObjectiveService,
    amount: Argument<Number>,
    private val options: ObjectiveOptions
) : AddonObjective<ItemStack>(service, amount, options, NotifyMessage.BQA_STONE_CUT) {

    fun onStonCutting(event: InventoryClickEvent, profile: OnlineProfile) {
        val top = event.view.topInventory
        if (top.type != InventoryType.STONECUTTER) return
        if (event.slotType != InventoryType.SlotType.RESULT) return

        val inv = top as? StonecutterInventory ?: return
        val result = inv.result ?: return

        if (result.type.isAir) return
        handle(profile, result, event)
    }

    override fun matchesTarget(profile: OnlineProfile, target: ItemStack): Boolean {
        val required = options.item?.getValue(profile)?.generate(profile) ?: return true
        return target.isSimilar(required)
    }
}