package kr.mrjimin.betonquestaddon.compatibility.itemsadder.objective

import dev.lone.itemsadder.api.CustomBlock
import dev.lone.itemsadder.api.Events.CustomBlockBreakEvent
import dev.lone.itemsadder.api.Events.CustomBlockInteractEvent
import dev.lone.itemsadder.api.Events.CustomBlockPlaceEvent
import kr.mrjimin.betonquestaddon.betonquest.objective.AddonObjective
import kr.mrjimin.betonquestaddon.config.NotifyMessage
import kr.mrjimin.betonquestaddon.util.DefaultOptions
import org.betonquest.betonquest.api.instruction.Argument
import org.betonquest.betonquest.api.profile.OnlineProfile
import org.betonquest.betonquest.api.quest.objective.service.ObjectiveService
import org.bukkit.block.Block
import org.bukkit.event.Cancellable

class ItemsAdderBlockObjective(
    service: ObjectiveService,
    options: DefaultOptions,
    notifyMessage: NotifyMessage,
    private val id: Argument<List<String>>
) : AddonObjective(service, options, notifyMessage) {

    fun onPlace(event: CustomBlockPlaceEvent, profile: OnlineProfile) {
        handle(profile, event.block, event)
    }

    fun onBreak(event: CustomBlockBreakEvent, profile: OnlineProfile) {
        handle(profile, event.block, event)
    }

    fun onInteract(event: CustomBlockInteractEvent, profile: OnlineProfile) {
        handle(profile, event.blockClicked, event)
    }

    private fun handle(profile: OnlineProfile, target: Block, event: Cancellable) {
        val targetId = CustomBlock.byAlreadyPlaced(target)?.namespacedID
        success(profile, id.getValue(profile).equals(targetId), event)
    }
}