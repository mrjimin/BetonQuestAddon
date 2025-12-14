package com.github.mrjimin.betonquestaddon.compatibilityold.advancedenchantments.objectives

import com.github.mrjimin.betonquestaddon.compatibilityold.BaseObjective
import com.github.mrjimin.betonquestaddon.compatibilityold.LangMessageKey
import net.advancedplugins.ae.api.TinkererTradeEvent
import org.betonquest.betonquest.api.instruction.Instruction
import org.betonquest.betonquest.api.instruction.variable.Variable
import org.bukkit.event.EventHandler

class AETinkererTradeObjective(
    instruction: Instruction,
    targetAmount: Variable<Number>
) : BaseObjective(instruction, targetAmount, LangMessageKey.AE_TINKERER_TRADE) {

    @EventHandler
    fun TinkererTradeEvent.onTinkererTrade() {
        handle(player)
    }

}