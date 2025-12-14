package com.github.mrjimin.betonquestaddon.compatibilityold.advancedenchantments.objectives

import com.github.mrjimin.betonquestaddon.compatibilityold.BaseObjective
import com.github.mrjimin.betonquestaddon.compatibilityold.LangMessageKey
import net.advancedplugins.ae.api.AlchemistTradeEvent
import org.betonquest.betonquest.api.instruction.Instruction
import org.betonquest.betonquest.api.instruction.variable.Variable
import org.bukkit.event.EventHandler

class AEAlchemistTradeObjective(
    instruction: Instruction,
    targetAmount: Variable<Number>
) : BaseObjective(instruction, targetAmount, LangMessageKey.AE_ALCHEMIST_TRADE) {

    @EventHandler
    fun AlchemistTradeEvent.onAlchemistTrade() {
        handle(player)
    }

}