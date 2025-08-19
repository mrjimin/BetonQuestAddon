package com.github.mrjimin.betonquestaddon.compatibility.headdatabase.items

import com.github.mrjimin.betonquestaddon.compatibility.headdatabase.HDBParser
import org.betonquest.betonquest.api.instruction.Instruction
import org.betonquest.betonquest.item.QuestItemWrapper
import org.betonquest.betonquest.kernel.registry.TypeFactory

object HDBItemFactory : TypeFactory<QuestItemWrapper> {
    override fun parseInstruction(instruction: Instruction): QuestItemWrapper {
        return HDBItemWrapper(instruction.get(HDBParser))
    }
}