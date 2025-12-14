package com.github.mrjimin.betonquestaddon.compatibilityold.coinsengine.events

import com.github.mrjimin.betonquestaddon.compatibilityold.LangMessageKey
import org.betonquest.betonquest.api.quest.QuestException
import org.betonquest.betonquest.api.quest.event.PlayerEvent
import org.betonquest.betonquest.api.quest.event.PlayerEventFactory
import org.betonquest.betonquest.config.PluginMessage
import org.betonquest.betonquest.kernel.processor.quest.VariableProcessor
import org.betonquest.betonquest.quest.event.IngameNotificationSender
import org.betonquest.betonquest.quest.event.NotificationLevel
import com.github.mrjimin.betonquestaddon.compatibilityold.coinsengine.events.CoinsEvent.Operation
import org.betonquest.betonquest.api.instruction.Instruction
import org.betonquest.betonquest.api.instruction.argument.Argument
import org.betonquest.betonquest.api.instruction.variable.Variable
import org.betonquest.betonquest.api.logger.BetonQuestLogger
import org.betonquest.betonquest.api.quest.PrimaryServerThreadData
import org.betonquest.betonquest.api.quest.event.thread.PrimaryServerThreadEvent

class CoinsEventFactory(
    private val logger: BetonQuestLogger,
    private val data: PrimaryServerThreadData,
    private val pluginMessage: PluginMessage,
    private val variableProcessor: VariableProcessor
) : PlayerEventFactory {

    override fun parsePlayer(instruction: Instruction): PlayerEvent {
        val currencyId = instruction.next()
        val rawAmount = instruction.next()

        val (operation, numericPart) = when {
            rawAmount.startsWith("+") -> Operation.ADD to rawAmount.substring(1)
            rawAmount.startsWith("-") -> Operation.REMOVE to rawAmount.substring(1)
            rawAmount.startsWith("*") -> Operation.MULTIPLY to rawAmount.substring(1)
            else -> Operation.SET to rawAmount
        }

        val amount = try {
            Variable(variableProcessor, instruction.getPackage(), numericPart, Argument.NUMBER)
        } catch (ex: QuestException) {
            throw QuestException("Failed to parse coin amount '$rawAmount': ${ex.message}", ex)
        }

        val notify = instruction.hasArgument("notify")
        val (givenSender, takenSender, resetSender) = if (notify) {
            val pack = instruction.getPackage()
            val fullID = instruction.id.full
            Triple(
                IngameNotificationSender(logger, pluginMessage, pack, fullID, NotificationLevel.INFO, LangMessageKey.COINS_GIVEN.key),
                IngameNotificationSender(logger, pluginMessage, pack, fullID, NotificationLevel.INFO, LangMessageKey.COINS_TAKEN.key),
                IngameNotificationSender(logger, pluginMessage, pack, fullID, NotificationLevel.INFO, LangMessageKey.COINS_RESET.key)
            )
        } else Triple(null, null, null)

        val event = CoinsEvent(currencyId, amount, operation, givenSender, takenSender, resetSender)
        return PrimaryServerThreadEvent(event, data)
    }
}
