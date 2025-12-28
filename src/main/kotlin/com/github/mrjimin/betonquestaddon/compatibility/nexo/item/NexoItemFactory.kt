package com.github.mrjimin.betonquestaddon.compatibility.nexo.item

import org.betonquest.betonquest.api.instruction.Instruction
import org.betonquest.betonquest.api.kernel.TypeFactory
import org.betonquest.betonquest.item.QuestItemTagAdapterWrapper
import org.betonquest.betonquest.item.QuestItemWrapper

class NexoItemFactory : TypeFactory<QuestItemWrapper> {
    override fun parseInstruction(instruction: Instruction): QuestItemWrapper? {
        val wrapper = NexoItemWrapper(instruction.string().get())
        return if (instruction.hasArgument("quest-item")) {
            QuestItemTagAdapterWrapper(wrapper)
        } else {
            wrapper
        }
    }
}