package com.github.mrjimin.betonquestaddon.compatibilityold.itemsadder.items

import com.github.mrjimin.betonquestaddon.compatibilityold.itemsadder.IaParser
import org.betonquest.betonquest.api.instruction.Instruction
import org.betonquest.betonquest.api.kernel.TypeFactory
import org.betonquest.betonquest.item.QuestItemWrapper

object IaItemFactory : TypeFactory<QuestItemWrapper> {
    override fun parseInstruction(instruction: Instruction): QuestItemWrapper {
        return IaItemWrapper(instruction.get(IaParser))
    }
}