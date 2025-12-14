package com.github.mrjimin.betonquestaddon.compatibilityold.craftengine.items

import com.github.mrjimin.betonquestaddon.compatibilityold.craftengine.CeParser
import org.betonquest.betonquest.api.instruction.Instruction
import org.betonquest.betonquest.api.kernel.TypeFactory
import org.betonquest.betonquest.item.QuestItemWrapper

object CeItemFactory : TypeFactory<QuestItemWrapper> {
    override fun parseInstruction(instruction: Instruction): QuestItemWrapper {
        return CeItemWrapper(instruction.get(CeParser))
    }
}