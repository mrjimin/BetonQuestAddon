package com.github.mrjimin.betonquestaddon.compatibility.craftengine.objective

import com.github.mrjimin.betonquestaddon.betonquest.objective.AbstractAddonObjectiveFactory
import com.github.mrjimin.betonquestaddon.config.NotifyMessage
import com.github.mrjimin.betonquestaddon.util.action.Action
import net.momirealms.craftengine.bukkit.api.event.FurnitureBreakEvent
import net.momirealms.craftengine.bukkit.api.event.FurnitureInteractEvent
import net.momirealms.craftengine.bukkit.api.event.FurniturePlaceEvent
import org.betonquest.betonquest.api.DefaultObjective
import org.betonquest.betonquest.api.instruction.Instruction
import org.betonquest.betonquest.api.quest.objective.service.ObjectiveService

class CraftEngineFurnitureObjectiveFactory(
    action: Action,
    notifyMessage: NotifyMessage
) : AbstractAddonObjectiveFactory(action, notifyMessage) {

    override fun parseInstruction(instruction: Instruction, service: ObjectiveService): DefaultObjective {
        val args = parseBaseArgs(instruction)
        val objective = CraftEngineFurnitureObjective(service, args.amount, args.ids, args.isCancelled, args.location, args.range, notifyMessage)

        return when (action) {
            Action.PLACE -> service.request(FurniturePlaceEvent::class.java).handler(objective::onPlace)
            Action.BREAK -> service.request(FurnitureBreakEvent::class.java).handler(objective::onBreak)
            Action.INTERACT -> service.request(FurnitureInteractEvent::class.java).handler(objective::onInteract)
        }.subscribe(true).let { objective }
    }
}