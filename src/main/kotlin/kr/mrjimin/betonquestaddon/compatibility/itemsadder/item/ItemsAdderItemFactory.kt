package kr.mrjimin.betonquestaddon.compatibility.itemsadder.item

import org.betonquest.betonquest.api.instruction.Instruction
import org.betonquest.betonquest.api.item.QuestItemWrapper
import org.betonquest.betonquest.api.quest.TypeFactory
import org.betonquest.betonquest.item.QuestItemTagAdapterWrapper

class ItemsAdderItemFactory : TypeFactory<QuestItemWrapper> {
    override fun parseInstruction(instruction: Instruction): QuestItemWrapper? {
        val wrapper = ItemsAdderItemWrapper(instruction.string().get())
        val questItem = instruction.bool().getFlag("quest-item", true).getValue(null).orElse(false)
        return if (questItem) {
            QuestItemTagAdapterWrapper(wrapper)
        } else {
            wrapper
        }
    }
}