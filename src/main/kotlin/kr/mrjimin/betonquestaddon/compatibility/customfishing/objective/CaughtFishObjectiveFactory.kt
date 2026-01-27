package kr.mrjimin.betonquestaddon.compatibility.customfishing.objective

import kr.mrjimin.betonquestaddon.compatibility.customfishing.FishingCaughtType
import kr.mrjimin.betonquestaddon.config.NotifyMessage
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
        val id = instruction.string().list().get()
        val targetAmount = instruction.number().get("amount", 1)

        val objective = CaughtFishObjective(service, targetAmount, id, notifyMessage)

        return when (fishingCaughtType) {
            FishingCaughtType.FISH -> service.request(FishingResultEvent::class.java).handler(objective::onFish)
            FishingCaughtType.GROUP -> service.request(FishingResultEvent::class.java).handler(objective::onFishGroup)
        }.subscribe(true).let { objective }
    }
}