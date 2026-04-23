package kr.mrjimin.betonquestaddon.compatibility.nexo.item

import org.betonquest.betonquest.api.instruction.Instruction
import org.betonquest.betonquest.api.quest.TypeFactory
import org.betonquest.betonquest.item.QuestItemTagAdapterWrapper
import org.betonquest.betonquest.api.item.QuestItemWrapper

class NexoItemFactory : TypeFactory<QuestItemWrapper> {
    override fun parseInstruction(instruction: Instruction): QuestItemWrapper {
        return NexoItemWrapper(instruction.string().get())
    }
}