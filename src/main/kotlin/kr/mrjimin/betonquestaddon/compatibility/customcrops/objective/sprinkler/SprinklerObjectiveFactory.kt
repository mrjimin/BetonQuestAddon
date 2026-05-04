package kr.mrjimin.betonquestaddon.compatibility.customcrops.objective.sprinkler

import kr.mrjimin.betonquestaddon.config.NotifyMessage
import kr.mrjimin.betonquestaddon.util.action.Action
import kr.mrjimin.betonquestaddon.util.parseOptions
import net.momirealms.customcrops.api.event.SprinklerBreakEvent
import net.momirealms.customcrops.api.event.SprinklerInteractEvent
import net.momirealms.customcrops.api.event.SprinklerPlaceEvent
import org.betonquest.betonquest.api.DefaultObjective
import org.betonquest.betonquest.api.instruction.Instruction
import org.betonquest.betonquest.api.quest.objective.ObjectiveFactory
import org.betonquest.betonquest.api.quest.objective.service.ObjectiveService
import org.bukkit.entity.Player

class SprinklerObjectiveFactory(
    private val action: Action,
    private val notifyMessage: NotifyMessage
) : ObjectiveFactory {

    override fun parseInstruction(instruction: Instruction, service: ObjectiveService): DefaultObjective {
        val (amount, options) = instruction.parseOptions()
        val objective = SprinklerObjective(service, amount, options, notifyMessage)

        return when (action) {
            Action.PLACE -> service.request(SprinklerPlaceEvent::class.java)
                .onlineHandler(objective::onPlace)

            Action.BREAK -> service.request(SprinklerBreakEvent::class.java)
                .onlineHandler(objective::onBreak)
                .player { it.entityBreaker() as? Player }

            Action.INTERACT -> service.request(SprinklerInteractEvent::class.java)
                .onlineHandler(objective::onInteract)
                .player { it.player }
        }.subscribe(true).let { objective }
    }
}