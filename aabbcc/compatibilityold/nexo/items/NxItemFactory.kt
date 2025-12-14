package com.github.mrjimin.betonquestaddon.compatibilityold.nexo.items

import com.github.mrjimin.betonquestaddon.compatibilityold.nexo.NxParser
import org.betonquest.betonquest.api.instruction.Instruction
import org.betonquest.betonquest.api.kernel.TypeFactory
import org.betonquest.betonquest.item.QuestItemWrapper

object NxItemFactory : TypeFactory<QuestItemWrapper> {
    override fun parseInstruction(instruction: Instruction): QuestItemWrapper {
        return NxItemWrapper(instruction.get(NxParser))
    }
}