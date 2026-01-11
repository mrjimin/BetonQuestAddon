package com.github.mrjimin.betonquestaddon.compatibility.nexo.objective

import com.github.mrjimin.betonquestaddon.config.NotifyMessage
import com.github.mrjimin.betonquestaddon.util.action.Action
import com.nexomc.nexo.api.events.custom_block.NexoBlockBreakEvent
import com.nexomc.nexo.api.events.custom_block.NexoBlockInteractEvent
import com.nexomc.nexo.api.events.custom_block.NexoBlockPlaceEvent
import org.betonquest.betonquest.api.DefaultObjective
import org.betonquest.betonquest.api.instruction.Instruction
import org.betonquest.betonquest.api.quest.objective.ObjectiveFactory
import org.betonquest.betonquest.api.quest.objective.service.ObjectiveService

class NexoBlockObjectiveFactory(
    private val action: Action,
    private val notifyMessage: NotifyMessage
) : ObjectiveFactory {
    override fun parseInstruction(instruction: Instruction, service: ObjectiveService): DefaultObjective {
        val id = instruction.string().list().get()
        val targetAmount = instruction.number().get("amount", 1)
        val isCancelled = instruction.bool().get("isCancelled", false)

        val objective = NexoBlockObjective(service, targetAmount, id, isCancelled, notifyMessage)

        return when (action) {
            Action.PLACE -> service.request(NexoBlockPlaceEvent::class.java).handler(objective::onPlace)
            Action.BREAK -> service.request(NexoBlockBreakEvent::class.java).handler(objective::onBreak)
            Action.INTERACT -> service.request(NexoBlockInteractEvent::class.java).handler(objective::onInteract)
        }.subscribe(true).let { objective }
    }
}