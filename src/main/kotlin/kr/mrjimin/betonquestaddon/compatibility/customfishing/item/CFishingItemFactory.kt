package kr.mrjimin.betonquestaddon.compatibility.customfishing.item

import net.momirealms.customfishing.api.mechanic.item.ItemManager
import org.betonquest.betonquest.api.instruction.Instruction
import org.betonquest.betonquest.api.kernel.TypeFactory
import org.betonquest.betonquest.item.QuestItemTagAdapterWrapper
import org.betonquest.betonquest.item.QuestItemWrapper

class CFishingItemFactory(
    private val itemManager: ItemManager
) : TypeFactory<QuestItemWrapper> {
    override fun parseInstruction(instruction: Instruction): QuestItemWrapper? {
        val wrapper = CFishingItemWrapper(instruction.string().get(), itemManager)
        val questItem = instruction.bool().getFlag("quest-item", true).getValue(null).orElse(false)
        return if (questItem) {
            QuestItemTagAdapterWrapper(wrapper)
        } else {
            wrapper
        }
    }
}