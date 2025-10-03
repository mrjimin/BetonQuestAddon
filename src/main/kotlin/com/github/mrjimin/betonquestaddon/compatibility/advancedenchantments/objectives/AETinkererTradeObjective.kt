package com.github.mrjimin.betonquestaddon.compatibility.advancedenchantments.objectives

import com.github.mrjimin.betonquestaddon.compatibility.AbstractBaseObjective
import com.github.mrjimin.betonquestaddon.compatibility.LangMessageKey
import net.advancedplugins.ae.api.TinkererTradeEvent
import org.betonquest.betonquest.api.instruction.Instruction
import org.betonquest.betonquest.api.instruction.variable.Variable
import org.betonquest.betonquest.api.logger.BetonQuestLogger
import org.bukkit.event.EventHandler

class AETinkererTradeObjective(
    instruction: Instruction,
    targetAmount: Variable<Number>
) : AbstractBaseObjective(instruction, targetAmount, LangMessageKey.AE_TINKERER_TRADE) {

    @EventHandler
    fun TinkererTradeEvent.onTinkererTrade() {
        handle(player)
    }

}