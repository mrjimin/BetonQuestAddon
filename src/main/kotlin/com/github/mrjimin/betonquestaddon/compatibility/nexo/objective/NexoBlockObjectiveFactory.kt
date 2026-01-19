package com.github.mrjimin.betonquestaddon.compatibility.nexo.objective

import com.github.mrjimin.betonquestaddon.betonquest.objective.AbstractAddonObjectiveFactory
import com.github.mrjimin.betonquestaddon.config.NotifyMessage
import com.github.mrjimin.betonquestaddon.util.action.Action
import com.nexomc.nexo.api.events.custom_block.NexoBlockBreakEvent
import com.nexomc.nexo.api.events.custom_block.NexoBlockInteractEvent
import com.nexomc.nexo.api.events.custom_block.NexoBlockPlaceEvent
import org.betonquest.betonquest.api.DefaultObjective
import org.betonquest.betonquest.api.QuestException
import org.betonquest.betonquest.api.instruction.Instruction
import org.betonquest.betonquest.api.quest.objective.Objective
import org.betonquest.betonquest.api.quest.objective.ObjectiveFactory
import org.betonquest.betonquest.api.quest.objective.service.ObjectiveService

class NexoBlockObjectiveFactory(
    action: Action,
    notifyMessage: NotifyMessage
) : AbstractAddonObjectiveFactory(action, notifyMessage) {

    override fun parseInstruction(instruction: Instruction, service: ObjectiveService): Objective? {
        val args = parseBaseArgs(instruction)
        val obj = NexoBlockObjective(service, args.amount, args.ids, args.isCancelled, args.location, args.range, notifyMessage)

        return when (action) {
            Action.PLACE -> service.request(NexoBlockPlaceEvent::class.java).handler(obj::onPlace)
            Action.BREAK -> service.request(NexoBlockBreakEvent::class.java).handler(obj::onBreak)
            Action.INTERACT -> service.request(NexoBlockInteractEvent::class.java).handler(obj::onInteract)
        }.subscribe(true).let { obj }
    }

}