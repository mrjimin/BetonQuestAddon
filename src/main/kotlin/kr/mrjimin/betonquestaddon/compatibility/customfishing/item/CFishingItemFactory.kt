package kr.mrjimin.betonquestaddon.compatibility.customfishing.item

import net.momirealms.customfishing.api.mechanic.item.ItemManager
import org.betonquest.betonquest.api.instruction.Instruction
import org.betonquest.betonquest.api.item.QuestItemWrapper
import org.betonquest.betonquest.api.quest.TypeFactory

class CFishingItemFactory(
    private val itemManager: ItemManager
) : TypeFactory<QuestItemWrapper> {
    override fun parseInstruction(instruction: Instruction): QuestItemWrapper {
        return CFishingItemWrapper(instruction.string().get(), itemManager)
    }
}