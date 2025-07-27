package com.github.mrjimin.betonquestaddon.compatibility.advancedenchantments.objectives

import com.github.mrjimin.betonquestaddon.compatibility.AbstractSimpleObjective
import com.github.mrjimin.betonquestaddon.compatibility.LangMessageKey
import net.advancedplugins.ae.api.TinkererTradeEvent
import org.betonquest.betonquest.api.logger.BetonQuestLogger
import org.betonquest.betonquest.instruction.Instruction
import org.betonquest.betonquest.instruction.variable.Variable
import org.bukkit.event.EventHandler

class AETinkererTradeObjective(
    instruction: Instruction,
    targetAmount: Variable<Number>,
    log: BetonQuestLogger
) : AbstractSimpleObjective(instruction, targetAmount, LangMessageKey.AE_TINKERER_TRADE, log) {

    @EventHandler
    fun TinkererTradeEvent.onTinkererTrade() {
        handle(player)
    }

}