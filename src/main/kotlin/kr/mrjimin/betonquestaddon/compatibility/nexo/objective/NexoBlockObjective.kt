package kr.mrjimin.betonquestaddon.compatibility.nexo.objective

import com.nexomc.nexo.api.NexoBlocks
import com.nexomc.nexo.api.events.custom_block.NexoBlockBreakEvent
import com.nexomc.nexo.api.events.custom_block.NexoBlockInteractEvent
import com.nexomc.nexo.api.events.custom_block.NexoBlockPlaceEvent
import kr.mrjimin.betonquestaddon.config.NotifyMessage
import kr.mrjimin.betonquestaddon.util.DefaultOptions
import org.betonquest.betonquest.api.CountingObjective
import org.betonquest.betonquest.api.instruction.Argument
import org.betonquest.betonquest.api.profile.OnlineProfile
import org.betonquest.betonquest.api.quest.objective.service.ObjectiveService
import org.bukkit.block.Block
import org.bukkit.event.Cancellable

class NexoBlockObjective(
    service: ObjectiveService,
    private val options: DefaultOptions,
    notifyMessage: NotifyMessage,
    private val id: Argument<List<String>>
) : CountingObjective(service, options.amount, notifyMessage.toKey()) {

    fun onPlace(event: NexoBlockPlaceEvent, profile: OnlineProfile) {
        handle(profile, event.block, event)
    }

    fun onBreak(event: NexoBlockBreakEvent, profile: OnlineProfile) {
        handle(profile, event.block, event)
    }

    fun onInteract(event: NexoBlockInteractEvent, profile: OnlineProfile) {
        handle(profile, event.block, event)
    }

    fun handle(profile: OnlineProfile, target: Block, event: Cancellable) {
        val targetId = NexoBlocks.customBlockMechanic(target)?.itemID

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