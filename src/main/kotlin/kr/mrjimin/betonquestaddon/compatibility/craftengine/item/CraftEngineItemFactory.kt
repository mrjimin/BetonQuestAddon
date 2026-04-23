package kr.mrjimin.betonquestaddon.compatibility.craftengine.item

import org.betonquest.betonquest.api.instruction.Instruction
import org.betonquest.betonquest.api.item.QuestItemWrapper
import org.betonquest.betonquest.api.quest.TypeFactory

class CraftEngineItemFactory : TypeFactory<QuestItemWrapper> {
    override fun parseInstruction(instruction: Instruction): QuestItemWrapper {
        return CraftEngineItemWrapper(instruction.string().get())
    }
}