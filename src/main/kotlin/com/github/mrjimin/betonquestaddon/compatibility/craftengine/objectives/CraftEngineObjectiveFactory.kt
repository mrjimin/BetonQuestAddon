package com.github.mrjimin.betonquestaddon.compatibility.craftengine.objectives

import com.github.mrjimin.betonquestaddon.util.action.ActionType
import net.momirealms.craftengine.bukkit.api.event.*
import org.betonquest.betonquest.api.DefaultObjective
import org.betonquest.betonquest.api.instruction.Instruction
import org.betonquest.betonquest.api.quest.objective.ObjectiveFactory
import org.betonquest.betonquest.api.quest.objective.event.ObjectiveFactoryService

class CraftEngineObjectiveFactory(
    private val actionType: ActionType
) : ObjectiveFactory {

    override fun parseInstruction(instruction: Instruction, service: ObjectiveFactoryService): DefaultObjective {
        val identifier = instruction.string().get()
        val targetAmount = instruction.number().get("amount", 1)

        val objective = CraftEngineObjective(service, targetAmount, actionType, identifier)

        return when (actionType) {
            ActionType.PLACE_BLOCK -> service.request(CustomBlockPlaceEvent::class.java).handler(objective::onBlockPlace)
            ActionType.BREAK_BLOCK -> service.request(CustomBlockBreakEvent::class.java).handler(objective::onBlockBreak)
            ActionType.INTERACT_BLOCK -> service.request(CustomBlockInteractEvent::class.java).handler(objective::onBlockInteract)
            ActionType.PLACE_FURNITURE -> service.request(FurniturePlaceEvent::class.java).handler(objective::onFurniturePlace)
            ActionType.BREAK_FURNITURE -> service.request(FurnitureBreakEvent::class.java).handler(objective::onFurnitureBreak)
            ActionType.INTERACT_FURNITURE -> service.request(FurnitureInteractEvent::class.java).handler(objective::onFurnitureInteract)
        }.subscribe(true).let { objective }
    }

}