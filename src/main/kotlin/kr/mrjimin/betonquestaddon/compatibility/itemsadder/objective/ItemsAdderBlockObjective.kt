package kr.mrjimin.betonquestaddon.compatibility.itemsadder.objective

import dev.lone.itemsadder.api.CustomBlock
import dev.lone.itemsadder.api.Events.CustomBlockBreakEvent
import dev.lone.itemsadder.api.Events.CustomBlockInteractEvent
import dev.lone.itemsadder.api.Events.CustomBlockPlaceEvent
import kr.mrjimin.betonquestaddon.config.NotifyMessage
import kr.mrjimin.betonquestaddon.util.DefaultOptions
import org.betonquest.betonquest.api.CountingObjective
import org.betonquest.betonquest.api.instruction.Argument
import org.betonquest.betonquest.api.profile.OnlineProfile
import org.betonquest.betonquest.api.quest.objective.service.ObjectiveService
import org.bukkit.block.Block
import org.bukkit.event.Cancellable

class ItemsAdderBlockObjective(
    service: ObjectiveService,
    private val options: DefaultOptions,
    notifyMessage: NotifyMessage,
    private val id: Argument<List<String>>
) : CountingObjective(service, options.amount, notifyMessage.toKey()) {

    fun onPlace(event: CustomBlockPlaceEvent, profile: OnlineProfile) {
        handle(profile, event.block, event)
    }

    fun onBreak(event: CustomBlockBreakEvent, profile: OnlineProfile) {
        handle(profile, event.block, event)
    }

    fun onInteract(event: CustomBlockInteractEvent, profile: OnlineProfile) {
        handle(profile, event.blockClicked, event)
    }

    fun handle(profile: OnlineProfile, target: Block, event: Cancellable) {
        val targetId = CustomBlock.byAlreadyPlaced(target)?.namespacedID

        options.locationFilter?.let { filter ->
            if (!filter.matches(profile, target.location)) return
        }

        if (options.isCancelled.getValue(profile)) {
            event.isCancelled = true
            return
        }

        if (id.getValue(profile).equals(targetId)) {
            getCountingData(profile)?.progress()
            completeIfDoneOrNotify(profile)
        }
    }
}