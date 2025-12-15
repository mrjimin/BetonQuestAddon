package com.github.seojimin0402.betonquestaddon.compatibility.craftengine.item

import org.betonquest.betonquest.api.instruction.Instruction
import org.betonquest.betonquest.api.instruction.argument.Argument
import org.betonquest.betonquest.api.kernel.TypeFactory
import org.betonquest.betonquest.item.QuestItemTagAdapterWrapper
import org.betonquest.betonquest.item.QuestItemWrapper

class CraftEngineItemFactory : TypeFactory<QuestItemWrapper> {
    override fun parseInstruction(instruction: Instruction): QuestItemWrapper? {
        val wrapper = CraftEngineItemWrapper(instruction[Argument.STRING])
        return if (instruction.hasArgument("quest-item")) {
            QuestItemTagAdapterWrapper(wrapper)
        } else {
            wrapper
        }
    }
}