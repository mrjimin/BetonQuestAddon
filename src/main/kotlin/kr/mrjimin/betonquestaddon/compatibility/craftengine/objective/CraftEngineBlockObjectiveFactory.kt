package kr.mrjimin.betonquestaddon.compatibility.craftengine.objective

import kr.mrjimin.betonquestaddon.config.NotifyMessage
import kr.mrjimin.betonquestaddon.util.action.Action
import kr.mrjimin.betonquestaddon.util.parseDefaultOptions
import net.momirealms.craftengine.bukkit.api.event.CustomBlockBreakEvent
import net.momirealms.craftengine.bukkit.api.event.CustomBlockInteractEvent
import net.momirealms.craftengine.bukkit.api.event.CustomBlockPlaceEvent
import org.betonquest.betonquest.api.DefaultObjective
import org.betonquest.betonquest.api.instruction.Instruction
import org.betonquest.betonquest.api.quest.objective.ObjectiveFactory
import org.betonquest.betonquest.api.quest.objective.service.ObjectiveService
import org.bukkit.event.EventPriority

class CraftEngineBlockObjectiveFactory(
    private val action: Action,
    private val notifyMessage: NotifyMessage
) : ObjectiveFactory {

    override fun parseInstruction(instruction: Instruction, service: ObjectiveService): DefaultObjective {
        val id = instruction.string().list().get()
        val options = instruction.parseDefaultOptions()
        val objective = CraftEngineBlockObjective(service, options, notifyMessage, id)

        return when (action) {
            Action.PLACE -> service.request(CustomBlockPlaceEvent::class.java)
                .onlineHandler(objective::onPlace)
                .priority(EventPriority.MONITOR)
                .player { it.player }

            Action.BREAK -> service.request(CustomBlockBreakEvent::class.java)
                .onlineHandler(objective::onBreak)
                .priority(EventPriority.MONITOR)
                .player { it.player }

            Action.INTERACT -> service.request(CustomBlockInteractEvent::class.java)
                .onlineHandler(objective::onInteract)
                .priority(EventPriority.MONITOR)
                .player { it.player }
        }.subscribe(true).let { objective }
    }
}