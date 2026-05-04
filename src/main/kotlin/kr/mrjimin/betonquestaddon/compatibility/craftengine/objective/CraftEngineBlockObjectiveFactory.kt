package kr.mrjimin.betonquestaddon.compatibility.craftengine.objective

import kr.mrjimin.betonquestaddon.config.NotifyMessage
import kr.mrjimin.betonquestaddon.util.action.Action
import kr.mrjimin.betonquestaddon.util.parseOptions
import net.momirealms.craftengine.bukkit.api.event.CustomBlockBreakEvent
import net.momirealms.craftengine.bukkit.api.event.CustomBlockInteractEvent
import net.momirealms.craftengine.bukkit.api.event.CustomBlockPlaceEvent
import org.betonquest.betonquest.api.DefaultObjective
import org.betonquest.betonquest.api.instruction.Instruction
import org.betonquest.betonquest.api.quest.objective.ObjectiveFactory
import org.betonquest.betonquest.api.quest.objective.service.ObjectiveService

class CraftEngineBlockObjectiveFactory(
    private val action: Action,
    private val notifyMessage: NotifyMessage
) : ObjectiveFactory {

    override fun parseInstruction(instruction: Instruction, service: ObjectiveService): DefaultObjective {
        val (amount, options) = instruction.parseOptions()
        val objective = CraftEngineBlockObjective(service, amount, options, notifyMessage)

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