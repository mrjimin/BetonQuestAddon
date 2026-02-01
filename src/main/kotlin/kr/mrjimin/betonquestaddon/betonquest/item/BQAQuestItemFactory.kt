package kr.mrjimin.betonquestaddon.betonquest.item

import kr.mrjimin.betonquestaddon.betonquest.item.components.BQAComponentRegistry
import kr.mrjimin.betonquestaddon.betonquest.item.components.DisplayNameComponents
import kr.mrjimin.betonquestaddon.betonquest.item.components.ItemComponents
import kr.mrjimin.betonquestaddon.item.ItemHandler
import org.betonquest.betonquest.api.instruction.Instruction
import org.betonquest.betonquest.api.item.QuestItem
import org.betonquest.betonquest.api.kernel.TypeFactory
import org.betonquest.betonquest.item.QuestItemWrapper
import org.bukkit.inventory.ItemStack

class BQAQuestItemFactory : TypeFactory<QuestItemWrapper> {
    override fun parseInstruction(instruction: Instruction): QuestItemWrapper {
        val identifier = instruction.nextElement()
        val components = mutableListOf<ItemComponents>()

        while (instruction.hasNext()) {
            val part = instruction.nextElement()
            if (part.isEmpty() || ':' !in part) continue

            val key = part.substringBefore(':')
            val data = part.substringAfter(':')

            components.add(BQAComponentRegistry.create(key, data))
        }

        return BQAQuestItemWrapper(createQuestItem(identifier, components))
    }

    private fun createQuestItem(identifier: String, components: List<ItemComponents>): QuestItem {
        val itemStack = ItemHandler.createItem(identifier)
        val itemMeta = itemStack.itemMeta

        components.forEach { it.apply(itemMeta) }
        itemStack.itemMeta = itemMeta

        components.forEach { it.apply(itemStack) }
        return BQAQuestItemWrapper.BQAQuestItem(itemStack)
    }
}