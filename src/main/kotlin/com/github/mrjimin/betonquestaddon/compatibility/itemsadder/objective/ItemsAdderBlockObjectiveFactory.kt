package com.github.mrjimin.betonquestaddon.compatibility.itemsadder.objective

import com.github.mrjimin.betonquestaddon.betonquest.objective.AbstractAddonObjectiveFactory
import com.github.mrjimin.betonquestaddon.config.NotifyMessage
import com.github.mrjimin.betonquestaddon.util.action.Action
import dev.lone.itemsadder.api.Events.CustomBlockBreakEvent
import dev.lone.itemsadder.api.Events.CustomBlockInteractEvent
import dev.lone.itemsadder.api.Events.CustomBlockPlaceEvent
import org.betonquest.betonquest.api.instruction.Instruction
import org.betonquest.betonquest.api.quest.objective.Objective
import org.betonquest.betonquest.api.quest.objective.service.ObjectiveService

class ItemsAdderBlockObjectiveFactory(
    action: Action,
    notifyMessage: NotifyMessage
) : AbstractAddonObjectiveFactory(action, notifyMessage) {

    override fun parseInstruction(instruction: Instruction, service: ObjectiveService): Objective {
        val args = parseBaseArgs(instruction)
        val objective = ItemsAdderBlockObjective(service, args.amount, args.ids, args.isCancelled, args.location, args.range, notifyMessage)

        return when (action) {
            Action.PLACE -> service.request(CustomBlockPlaceEvent::class.java).handler(objective::onPlace)
            Action.BREAK -> service.request(CustomBlockBreakEvent::class.java).handler(objective::onBreak)
            Action.INTERACT -> service.request(CustomBlockInteractEvent::class.java).handler(objective::onInteract)
        }.subscribe(true).let { objective }
    }
}