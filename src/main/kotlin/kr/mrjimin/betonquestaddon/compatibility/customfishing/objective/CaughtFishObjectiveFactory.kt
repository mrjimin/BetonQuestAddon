package kr.mrjimin.betonquestaddon.compatibility.customfishing.objective

import kr.mrjimin.betonquestaddon.compatibility.customfishing.FishingCaughtType
import kr.mrjimin.betonquestaddon.config.NotifyMessage
import kr.mrjimin.betonquestaddon.util.parseDefaultOptions
import net.momirealms.customfishing.api.event.FishingResultEvent
import org.betonquest.betonquest.api.DefaultObjective
import org.betonquest.betonquest.api.instruction.Instruction
import org.betonquest.betonquest.api.quest.objective.ObjectiveFactory
import org.betonquest.betonquest.api.quest.objective.service.ObjectiveService
import org.bukkit.event.EventPriority

class CaughtFishObjectiveFactory(
    private val fishingCaughtType: FishingCaughtType,
    private val notifyMessage: NotifyMessage
) : ObjectiveFactory {

    override fun parseInstruction(instruction: Instruction, service: ObjectiveService): DefaultObjective {
        val id = instruction.string().list().get()
        val options = instruction.parseDefaultOptions()
        val objective = CaughtFishObjective(service, options, notifyMessage, id)

        return when (fishingCaughtType) {
            FishingCaughtType.FISH -> service.request(FishingResultEvent::class.java)
                .onlineHandler(objective::onFish)
                .priority(EventPriority.MONITOR)
                .player { it.player }
            FishingCaughtType.GROUP -> service.request(FishingResultEvent::class.java)
                .onlineHandler(objective::onFishGroup)
                .priority(EventPriority.MONITOR)
                .player { it.player }
        }.subscribe(true).let { objective }
    }
}