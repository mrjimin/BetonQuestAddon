package kr.mrjimin.betonquestaddon.compatibility.customcrops.objective.pot

import kr.mrjimin.betonquestaddon.config.NotifyMessage
import kr.mrjimin.betonquestaddon.util.action.Action
import kr.mrjimin.betonquestaddon.util.parseOptions
import net.momirealms.customcrops.api.event.PotBreakEvent
import net.momirealms.customcrops.api.event.PotInteractEvent
import net.momirealms.customcrops.api.event.PotPlaceEvent
import org.betonquest.betonquest.api.DefaultObjective
import org.betonquest.betonquest.api.instruction.Instruction
import org.betonquest.betonquest.api.quest.objective.ObjectiveFactory
import org.betonquest.betonquest.api.quest.objective.service.ObjectiveService
import org.bukkit.entity.Player

class PotObjectiveFactory(
    private val action: Action,
    private val notifyMessage: NotifyMessage
) : ObjectiveFactory {

    override fun parseInstruction(instruction: Instruction, service: ObjectiveService): DefaultObjective {
        val (amount, options) = instruction.parseOptions()
        val objective = PotObjective(service, amount, options, notifyMessage)

        return when (action) {
            Action.PLACE -> service.request(PotPlaceEvent::class.java)
                .onlineHandler(objective::onPlace)
                .player { it.player }

            Action.BREAK -> service.request(PotBreakEvent::class.java)
                .onlineHandler(objective::onBreak)
                .player { it.entityBreaker() as? Player }

            Action.INTERACT -> service.request(PotInteractEvent::class.java)
                .onlineHandler(objective::onInteract)
                .player { it.player }
        }.subscribe(true).let { objective }
    }
}