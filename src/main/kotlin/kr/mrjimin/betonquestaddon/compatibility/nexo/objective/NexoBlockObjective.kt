package kr.mrjimin.betonquestaddon.compatibility.nexo.objective

import com.nexomc.nexo.api.NexoBlocks
import com.nexomc.nexo.api.events.custom_block.NexoBlockBreakEvent
import com.nexomc.nexo.api.events.custom_block.NexoBlockInteractEvent
import com.nexomc.nexo.api.events.custom_block.NexoBlockPlaceEvent
import kr.mrjimin.betonquestaddon.betonquest.objective.AddonObjective
import kr.mrjimin.betonquestaddon.config.NotifyMessage
import kr.mrjimin.betonquestaddon.util.DefaultOptions
import org.betonquest.betonquest.api.instruction.Argument
import org.betonquest.betonquest.api.profile.OnlineProfile
import org.betonquest.betonquest.api.quest.objective.service.ObjectiveService
import org.bukkit.block.Block
import org.bukkit.event.Cancellable

class NexoBlockObjective(
    service: ObjectiveService,
    options: DefaultOptions,
    notifyMessage: NotifyMessage,
    private val id: Argument<List<String>>
) : AddonObjective(service, options, notifyMessage) {

    fun onPlace(event: NexoBlockPlaceEvent, profile: OnlineProfile) {
        handle(profile, event.block, event)
    }

    fun onBreak(event: NexoBlockBreakEvent, profile: OnlineProfile) {
        handle(profile, event.block, event)
    }

    fun onInteract(event: NexoBlockInteractEvent, profile: OnlineProfile) {
        handle(profile, event.block, event)
    }

    private fun handle(profile: OnlineProfile, target: Block, event: Cancellable) {
        val targetId = NexoBlocks.customBlockMechanic(target)?.itemID
        success(profile, id.getValue(profile).equals(targetId), event)
    }

}