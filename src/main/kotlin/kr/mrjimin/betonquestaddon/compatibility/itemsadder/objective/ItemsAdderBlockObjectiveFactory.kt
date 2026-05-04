package kr.mrjimin.betonquestaddon.compatibility.itemsadder.objective

import dev.lone.itemsadder.api.Events.CustomBlockBreakEvent
import dev.lone.itemsadder.api.Events.CustomBlockInteractEvent
import dev.lone.itemsadder.api.Events.CustomBlockPlaceEvent
import kr.mrjimin.betonquestaddon.config.NotifyMessage
import kr.mrjimin.betonquestaddon.util.action.Action
import kr.mrjimin.betonquestaddon.util.parseOptions
import org.betonquest.betonquest.api.instruction.Instruction
import org.betonquest.betonquest.api.quest.objective.Objective
import org.betonquest.betonquest.api.quest.objective.ObjectiveFactory
import org.betonquest.betonquest.api.quest.objective.service.ObjectiveService

class ItemsAdderBlockObjectiveFactory(
    private val action: Action,
    private val notifyMessage: NotifyMessage
) : ObjectiveFactory {

    override fun parseInstruction(instruction: Instruction, service: ObjectiveService): Objective {
        val (amount, options) = instruction.parseOptions()
        val objective = ItemsAdderBlockObjective(service, amount, options, notifyMessage)

        return when (action) {
            Action.PLACE -> service.request(CustomBlockPlaceEvent::class.java)
                .onlineHandler(objective::onPlace)
                .player { it.player }

            Action.BREAK -> service.request(CustomBlockBreakEvent::class.java)
                .onlineHandler(objective::onBreak)
                .player { it.player }

            Action.INTERACT -> service.request(CustomBlockInteractEvent::class.java)
                .onlineHandler(objective::onInteract)
                .player { it.player }
        }.subscribe(true).let { objective }
    }
}