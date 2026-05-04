package kr.mrjimin.betonquestaddon.compatibility.customfishing.objective

import kr.mrjimin.betonquestaddon.compatibility.customfishing.FishingCaughtType
import kr.mrjimin.betonquestaddon.config.NotifyMessage
import kr.mrjimin.betonquestaddon.util.parseOptions
import net.momirealms.customfishing.api.event.FishingResultEvent
import org.betonquest.betonquest.api.DefaultObjective
import org.betonquest.betonquest.api.instruction.Instruction
import org.betonquest.betonquest.api.quest.objective.ObjectiveFactory
import org.betonquest.betonquest.api.quest.objective.service.ObjectiveService

class CaughtFishObjectiveFactory(
    private val fishingCaughtType: FishingCaughtType,
    private val notifyMessage: NotifyMessage
) : ObjectiveFactory {

    override fun parseInstruction(instruction: Instruction, service: ObjectiveService): DefaultObjective {
        val (amount, options) = instruction.parseOptions()
        val objective = CaughtFishObjective(service, amount, options, notifyMessage)

        return when (fishingCaughtType) {
            FishingCaughtType.FISH -> service.request(FishingResultEvent::class.java)
                .onlineHandler(objective::onFish)
                .player { it.player }
            FishingCaughtType.GROUP -> service.request(FishingResultEvent::class.java)
                .onlineHandler(objective::onFishGroup)
                .player { it.player }
        }.subscribe(true).let { objective }
    }
}