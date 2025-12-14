package com.github.mrjimin.betonquestaddon.compatibilityold.headdatabase.items

import com.github.mrjimin.betonquestaddon.compatibilityold.headdatabase.HDBParser
import org.betonquest.betonquest.api.instruction.Instruction
import org.betonquest.betonquest.api.kernel.TypeFactory
import org.betonquest.betonquest.item.QuestItemWrapper

object HDBItemFactory : TypeFactory<QuestItemWrapper> {
    override fun parseInstruction(instruction: Instruction): QuestItemWrapper {
        return HDBItemWrapper(instruction.get(HDBParser))
    }
}