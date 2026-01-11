package com.github.mrjimin.betonquestaddon.compatibility.craftengine.objective

import com.github.mrjimin.betonquestaddon.config.NotifyMessage
import com.github.mrjimin.betonquestaddon.util.action.Action
import net.momirealms.craftengine.bukkit.api.event.CustomBlockBreakEvent
import net.momirealms.craftengine.bukkit.api.event.CustomBlockInteractEvent
import net.momirealms.craftengine.bukkit.api.event.CustomBlockPlaceEvent
import org.betonquest.betonquest.api.DefaultObjective
import org.betonquest.betonquest.api.instruction.Instruction
import org.betonquest.betonquest.api.quest.objective.ObjectiveFactory
import org.betonquest.betonquest.api.quest.objective.service.ObjectiveService

class CraftEngineBlockObjectiveFactory(
    private val action: Action,
    private val notifyMessage: NotifyMessage
) : ObjectiveFactory {
    override fun parseInstruction(instruction: Instruction, service: ObjectiveService): DefaultObjective {
        val id = instruction.string().list().get()
        val targetAmount = instruction.number().get("amount", 1)
        val isCancelled = instruction.bool().get("isCancelled", false)

        val objective = CraftEngineBlockObjective(service, targetAmount, id, isCancelled, notifyMessage)

        return when (action) {
            Action.PLACE -> service.request(CustomBlockPlaceEvent::class.java).handler(objective::onPlace)
            Action.BREAK -> service.request(CustomBlockBreakEvent::class.java).handler(objective::onBreak)
            Action.INTERACT -> service.request(CustomBlockInteractEvent::class.java).handler(objective::onInteract)
        }.subscribe(true).let { objective }
    }
}