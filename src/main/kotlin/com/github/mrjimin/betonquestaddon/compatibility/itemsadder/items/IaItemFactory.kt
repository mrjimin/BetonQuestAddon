package com.github.mrjimin.betonquestaddon.compatibility.itemsadder.items

import com.github.mrjimin.betonquestaddon.compatibility.itemsadder.IaParser
import org.betonquest.betonquest.api.instruction.Instruction
import org.betonquest.betonquest.item.QuestItemWrapper
import org.betonquest.betonquest.kernel.registry.TypeFactory

object IaItemFactory : TypeFactory<QuestItemWrapper> {
    override fun parseInstruction(instruction: Instruction): QuestItemWrapper {
        return IaItemWrapper(instruction.get(IaParser))
    }
}