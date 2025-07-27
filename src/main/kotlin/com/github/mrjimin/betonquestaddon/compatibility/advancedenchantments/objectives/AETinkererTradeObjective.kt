package com.github.mrjimin.betonquestaddon.compatibility.advancedenchantments.objectives

import com.github.mrjimin.betonquestaddon.compatibility.AbstractSimpleObjective
import com.github.mrjimin.betonquestaddon.compatibility.LangMessageKey
import net.advancedplugins.ae.api.TinkererTradeEvent
import org.betonquest.betonquest.api.logger.BetonQuestLogger
import org.betonquest.betonquest.instruction.Instruction
import org.betonquest.betonquest.instruction.variable.Variable
import org.bukkit.event.EventHandler
import org.bukkit.event.EventPriority

class AETinkererTradeObjective(
    instruction: Instruction,
    targetAmount: Variable<Number>,
    log: BetonQuestLogger
) : AbstractSimpleObjective(instruction, targetAmount, LangMessageKey.AE_TINKERER_TRADE, log) {

    @EventHandler(ignoreCancelled = true, priority = EventPriority.MONITOR)
    fun TinkererTradeEvent.onTinkererTrade() {
        handle(player)
    }

}