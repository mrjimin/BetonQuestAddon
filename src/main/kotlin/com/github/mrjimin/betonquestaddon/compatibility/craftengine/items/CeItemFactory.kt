package com.github.mrjimin.betonquestaddon.compatibility.craftengine.items

import com.github.mrjimin.betonquestaddon.compatibility.craftengine.CeParser
import org.betonquest.betonquest.api.instruction.Instruction
import org.betonquest.betonquest.item.QuestItemWrapper
import org.betonquest.betonquest.kernel.registry.TypeFactory

object CeItemFactory : TypeFactory<QuestItemWrapper> {
    override fun parseInstruction(instruction: Instruction): QuestItemWrapper {
        return CeItemWrapper(instruction.get(CeParser))
    }
}