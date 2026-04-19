package kr.mrjimin.betonquestaddon.compatibility.nexo.objective

import com.nexomc.nexo.api.events.custom_block.NexoBlockBreakEvent
import com.nexomc.nexo.api.events.custom_block.NexoBlockInteractEvent
import com.nexomc.nexo.api.events.custom_block.NexoBlockPlaceEvent
import kr.mrjimin.betonquestaddon.betonquest.objective.AbstractAddonObjectiveFactory
import kr.mrjimin.betonquestaddon.config.NotifyMessage
import kr.mrjimin.betonquestaddon.util.action.Action
import org.betonquest.betonquest.api.instruction.Instruction
import org.betonquest.betonquest.api.quest.objective.Objective
import org.betonquest.betonquest.api.quest.objective.service.ObjectiveService

class NexoBlockObjectiveFactory(
    action: Action,
    notifyMessage: NotifyMessage
) : AbstractAddonObjectiveFactory(action, notifyMessage) {

    override fun parseInstruction(instruction: Instruction, service: ObjectiveService): Objective {
        val args = parseBaseArgs(instruction)
        val objective = NexoBlockObjective(service, args.amount, args.ids, args.isCancelled, args.location, args.range, notifyMessage)

        return when (action) {
            Action.PLACE -> service.request(NexoBlockPlaceEvent::class.java)
                .onlineHandler(objective::onPlace)
                .player { it.player }
            Action.BREAK -> service.request(NexoBlockBreakEvent::class.java)
                .onlineHandler(objective::onBreak)
                .player { it.player }
            Action.INTERACT -> service.request(NexoBlockInteractEvent::class.java)
                .onlineHandler(objective::onInteract)
                .player { it.player }
        }.subscribe(true).let { objective }
    }

}