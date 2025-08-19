package com.github.mrjimin.betonquestaddon.betonquest.items

import com.github.mrjimin.betonquestaddon.betonquest.parser.BQAddonParser
import org.betonquest.betonquest.api.instruction.Instruction
import org.betonquest.betonquest.item.QuestItemWrapper
import org.betonquest.betonquest.kernel.registry.TypeFactory

object BQAddonItemFactory : TypeFactory<QuestItemWrapper> {
    override fun parseInstruction(instruction: Instruction): QuestItemWrapper {
        return BQAddonItemWrapper(instruction.get(BQAddonParser))
    }
}