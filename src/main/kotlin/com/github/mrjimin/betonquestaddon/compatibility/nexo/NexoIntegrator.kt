package com.github.mrjimin.betonquestaddon.compatibility.nexo

import com.github.mrjimin.betonquestaddon.compatibility.ICompatibility
import com.github.mrjimin.betonquestaddon.compatibility.nexo.action.NexoActionFactory
import com.github.mrjimin.betonquestaddon.compatibility.nexo.item.NexoItemFactory
import com.github.mrjimin.betonquestaddon.compatibility.nexo.item.NexoQuestItemSerializer
import com.github.mrjimin.betonquestaddon.compatibility.nexo.objectives.NexoObjectiveFactory
import com.github.mrjimin.betonquestaddon.conditions.BaseConditionFactory
import com.github.mrjimin.betonquestaddon.util.action.ActionType
import com.github.mrjimin.betonquestaddon.util.action.TargetType
import com.nexomc.nexo.api.NexoBlocks
import com.nexomc.nexo.api.NexoFurniture
import org.betonquest.betonquest.api.BetonQuestApi

class NexoIntegrator : ICompatibility {

    override fun hook(api: BetonQuestApi) {
        val questRegistries = api.questRegistries

        val itemRegistry = api.featureRegistries.item()
        itemRegistry.register("nexo", NexoItemFactory())
        itemRegistry.registerSerializer("nexo", NexoQuestItemSerializer())

        val condition = questRegistries.condition()
        condition.registerCombined(
            "nexoBlock",
            BaseConditionFactory { location ->
                NexoBlocks.customBlockMechanic(location)?.itemID
            }
        )
        condition.registerCombined(
            "nexoFurniture",
            BaseConditionFactory { location ->
                NexoFurniture.furnitureMechanic(location)?.itemID
            }
        )

        val action = questRegistries.action()
        action.register(
            "nexoBlockAt",
            NexoActionFactory(TargetType.BLOCK)
        )
        action.register(
            "nexoFurnitureAt",
            NexoActionFactory(TargetType.FURNITURE)
        )

        val objective = questRegistries.objective()
        objective.register("nexoBlockBreak", NexoObjectiveFactory(TargetType.BLOCK, ActionType.BREAK))
        objective.register("nexoBlockPlace", NexoObjectiveFactory(TargetType.BLOCK, ActionType.PLACE))
        objective.register("nexoBlockInteract", NexoObjectiveFactory(TargetType.BLOCK, ActionType.INTERACT))

        objective.register("nexoFurnitureBreak", NexoObjectiveFactory(TargetType.FURNITURE, ActionType.BREAK))
        objective.register("nexoFurniturePlace", NexoObjectiveFactory(TargetType.FURNITURE, ActionType.PLACE))
        objective.register("nexoFurnitureInteract", NexoObjectiveFactory(TargetType.FURNITURE, ActionType.INTERACT))
    }
}
