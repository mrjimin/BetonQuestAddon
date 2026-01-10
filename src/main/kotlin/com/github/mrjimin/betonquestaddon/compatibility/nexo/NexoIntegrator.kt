package com.github.mrjimin.betonquestaddon.compatibility.nexo

import com.github.mrjimin.betonquestaddon.compatibility.ICompatibility
import com.github.mrjimin.betonquestaddon.compatibility.nexo.action.NexoActionFactory
import com.github.mrjimin.betonquestaddon.compatibility.nexo.item.NexoItemFactory
import com.github.mrjimin.betonquestaddon.compatibility.nexo.item.NexoQuestItemSerializer
import com.github.mrjimin.betonquestaddon.compatibility.nexo.objectives.NexoObjectiveFactory
import com.github.mrjimin.betonquestaddon.conditions.LocationConditionFactory
import com.github.mrjimin.betonquestaddon.util.action.ActionType
import com.github.mrjimin.betonquestaddon.util.action.TargetType
import com.nexomc.nexo.api.NexoBlocks
import com.nexomc.nexo.api.NexoFurniture
import org.betonquest.betonquest.api.BetonQuestApi

class NexoIntegrator : ICompatibility {

    override fun hook(api: BetonQuestApi) {
        val questRegistries = api.questRegistries

        api.featureRegistries.item().apply {
            register("nexo", NexoItemFactory())
            registerSerializer("nexo", NexoQuestItemSerializer())
        }

        questRegistries.condition().apply {
            registerCombined("nexoBlock", LocationConditionFactory { location ->
                    NexoBlocks.customBlockMechanic(location)?.itemID
                }
            )
            registerCombined("nexoFurniture", LocationConditionFactory { location ->
                    NexoFurniture.furnitureMechanic(location)?.itemID
                }
            )
        }

        questRegistries.action().apply {
            register("nexoBlockAt", NexoActionFactory(TargetType.BLOCK))
            register("nexoFurnitureAt", NexoActionFactory(TargetType.FURNITURE))
        }

        questRegistries.objective().apply {
            register("nexoBlockPlace", NexoObjectiveFactory(ActionType.PLACE_BLOCK))
            register("nexoBlockBreak", NexoObjectiveFactory(ActionType.BREAK_BLOCK))
            register("nexoBlockInteract", NexoObjectiveFactory(ActionType.INTERACT_BLOCK))

            register("nexoFurniturePlace", NexoObjectiveFactory(ActionType.PLACE_FURNITURE))
            register("nexoFurnitureBreak", NexoObjectiveFactory(ActionType.BREAK_FURNITURE))
            register("nexoFurnitureInteract", NexoObjectiveFactory(ActionType.INTERACT_FURNITURE))
        }
    }
}
