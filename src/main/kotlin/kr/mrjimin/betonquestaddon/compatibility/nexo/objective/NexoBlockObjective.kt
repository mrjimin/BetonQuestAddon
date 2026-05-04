package kr.mrjimin.betonquestaddon.compatibility.nexo.objective

import com.nexomc.nexo.api.NexoBlocks
import com.nexomc.nexo.api.events.custom_block.NexoBlockBreakEvent
import com.nexomc.nexo.api.events.custom_block.NexoBlockInteractEvent
import com.nexomc.nexo.api.events.custom_block.NexoBlockPlaceEvent
import kr.mrjimin.betonquestaddon.betonquest.objective.AddonObjective
import kr.mrjimin.betonquestaddon.config.NotifyMessage
import kr.mrjimin.betonquestaddon.util.ObjectiveOptions
import org.betonquest.betonquest.api.instruction.Argument
import org.betonquest.betonquest.api.profile.OnlineProfile
import org.betonquest.betonquest.api.quest.objective.service.ObjectiveService
import org.bukkit.Location
import org.bukkit.block.Block

class NexoBlockObjective(
    service: ObjectiveService,
    amount: Argument<Number>,
    options: ObjectiveOptions,
    notifyMessage: NotifyMessage
) : AddonObjective<Block>(service, amount, options, notifyMessage) {

    fun onPlace(event: NexoBlockPlaceEvent, profile: OnlineProfile) {
        handle(profile, event.block, event)
    }

    fun onBreak(event: NexoBlockBreakEvent, profile: OnlineProfile) {
        handle(profile, event.block, event)
    }

    fun onInteract(event: NexoBlockInteractEvent, profile: OnlineProfile) {
        handle(profile, event.block, event)
    }

    override fun getId(target: Block): String? {
        return NexoBlocks.customBlockMechanic(target)?.itemID
    }
    override fun getLocation(target: Block): Location {
        return target.location
    }
}