package com.github.mrjimin.betonquestaddon.compatibility.itemsadder.item

import com.github.mrjimin.betonquestaddon.compatibility.itemsadder.CustomStackParser
import org.betonquest.betonquest.api.instruction.Instruction
import org.betonquest.betonquest.api.kernel.TypeFactory
import org.betonquest.betonquest.item.QuestItemTagAdapterWrapper
import org.betonquest.betonquest.item.QuestItemWrapper

class ItemsAdderItemFactory : TypeFactory<QuestItemWrapper> {
    override fun parseInstruction(instruction: Instruction): QuestItemWrapper? {
        val wrapper = ItemsAdderItemWrapper(instruction.parse(CustomStackParser).get())
        return if (instruction.hasArgument("quest-item")) {
            QuestItemTagAdapterWrapper(wrapper)
        } else {
            wrapper
        }
    }
}