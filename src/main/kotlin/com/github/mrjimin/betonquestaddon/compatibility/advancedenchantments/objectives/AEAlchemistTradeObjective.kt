package com.github.mrjimin.betonquestaddon.compatibility.advancedenchantments.objectives

import com.github.mrjimin.betonquestaddon.compatibility.AbstractSimpleObjective
import com.github.mrjimin.betonquestaddon.compatibility.LangMessageKey
import net.advancedplugins.ae.api.AlchemistTradeEvent
import org.betonquest.betonquest.api.instruction.Instruction
import org.betonquest.betonquest.api.logger.BetonQuestLogger
import org.betonquest.betonquest.api.instruction.variable.Variable
import org.bukkit.event.EventHandler

class AEAlchemistTradeObjective(
    instruction: Instruction,
    targetAmount: Variable<Number>,
    log: BetonQuestLogger
) : AbstractSimpleObjective(instruction, targetAmount, LangMessageKey.AE_ALCHEMIST_TRADE, log) {

    @EventHandler
    fun AlchemistTradeEvent.onAlchemistTrade() {
        handle(player)
    }

}