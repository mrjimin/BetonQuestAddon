package com.github.mrjimin.betonquestaddon.compatibility.customnameplates.condition

import com.github.mrjimin.betonquestaddon.compatibility.customnameplates.NpCheckType
import org.betonquest.betonquest.api.instruction.Instruction
import org.betonquest.betonquest.api.logger.BetonQuestLoggerFactory
import org.betonquest.betonquest.api.quest.condition.PlayerCondition
import org.betonquest.betonquest.api.quest.condition.PlayerConditionFactory
import org.betonquest.betonquest.api.quest.condition.online.OnlineConditionAdapter

class NpEquippedConditionFactory(
    private val type: NpCheckType,
    private val loggerFactory: BetonQuestLoggerFactory
): PlayerConditionFactory {
    override fun parsePlayer(instruction: Instruction): PlayerCondition {
        val target = instruction.string().get()
        val log = loggerFactory.create(NpEquippedCondition::class.java)
        return OnlineConditionAdapter(NpEquippedCondition(type, target), log, instruction.`package`)
    }
}