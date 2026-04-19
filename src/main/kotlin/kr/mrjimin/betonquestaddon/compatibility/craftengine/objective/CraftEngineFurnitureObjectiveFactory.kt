package kr.mrjimin.betonquestaddon.compatibility.craftengine.objective

import kr.mrjimin.betonquestaddon.betonquest.objective.AbstractAddonObjectiveFactory
import kr.mrjimin.betonquestaddon.config.NotifyMessage
import kr.mrjimin.betonquestaddon.util.action.Action
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
            Action.PLACE -> service.request(FurniturePlaceEvent::class.java)
                .onlineHandler(objective::onPlace)
                .player { it.player }
            Action.BREAK -> service.request(FurnitureBreakEvent::class.java)
                .onlineHandler(objective::onBreak)
                .player { it.player }
            Action.INTERACT -> service.request(FurnitureInteractEvent::class.java)
                .onlineHandler(objective::onInteract)
                .player { it.player }
        }.subscribe(true).let { objective }
    }
}