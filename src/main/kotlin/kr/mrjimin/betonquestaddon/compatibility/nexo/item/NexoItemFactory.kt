package kr.mrjimin.betonquestaddon.compatibility.nexo.item

import org.betonquest.betonquest.api.instruction.Instruction
import org.betonquest.betonquest.api.item.QuestItemWrapper
import org.betonquest.betonquest.api.quest.TypeFactory

class NexoItemFactory : TypeFactory<QuestItemWrapper> {
    override fun parseInstruction(instruction: Instruction): QuestItemWrapper {
        return NexoItemWrapper(instruction.string().get())
    }
}