package kr.mrjimin.betonquestaddon.compatibility.craftengine.objective

import kr.mrjimin.betonquestaddon.config.NotifyMessage
import kr.mrjimin.betonquestaddon.util.action.Action
import kr.mrjimin.betonquestaddon.util.parseDefaultOptions
import net.momirealms.craftengine.bukkit.api.event.FurnitureBreakEvent
import net.momirealms.craftengine.bukkit.api.event.FurnitureInteractEvent
import net.momirealms.craftengine.bukkit.api.event.FurniturePlaceEvent
import org.betonquest.betonquest.api.DefaultObjective
import org.betonquest.betonquest.api.instruction.Instruction
import org.betonquest.betonquest.api.quest.objective.ObjectiveFactory
import org.betonquest.betonquest.api.quest.objective.service.ObjectiveService
import org.bukkit.event.EventPriority

class CraftEngineFurnitureObjectiveFactory(
    private val action: Action,
    private val notifyMessage: NotifyMessage
) : ObjectiveFactory {

    override fun parseInstruction(instruction: Instruction, service: ObjectiveService): DefaultObjective {
        val id = instruction.string().list().get()
        val options = instruction.parseDefaultOptions()
        val objective = CraftEngineFurnitureObjective(service,options, notifyMessage, id)

        return when (action) {
            Action.PLACE -> service.request(FurniturePlaceEvent::class.java)
                .onlineHandler(objective::onPlace)
                .priority(EventPriority.MONITOR)
                .player { it.player }

            Action.BREAK -> service.request(FurnitureBreakEvent::class.java)
                .onlineHandler(objective::onBreak)
                .priority(EventPriority.MONITOR)
                .player { it.player }

            Action.INTERACT -> service.request(FurnitureInteractEvent::class.java)
                .onlineHandler(objective::onInteract)
                .priority(EventPriority.MONITOR)
                .player { it.player }
        }.subscribe(true).let { objective }
    }
}