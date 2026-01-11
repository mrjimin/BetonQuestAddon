package com.github.mrjimin.betonquestaddon.compatibility.nexo

import com.github.mrjimin.betonquestaddon.compatibility.ICompatibility
import com.github.mrjimin.betonquestaddon.compatibility.nexo.action.NexoSetBlockActionFactory
import com.github.mrjimin.betonquestaddon.compatibility.nexo.action.NexoSetFurnitureActionFactory
import com.github.mrjimin.betonquestaddon.compatibility.nexo.item.NexoItemFactory
import com.github.mrjimin.betonquestaddon.compatibility.nexo.item.NexoQuestItemSerializer
import com.github.mrjimin.betonquestaddon.compatibility.nexo.objective.NexoBlockObjectiveFactory
import com.github.mrjimin.betonquestaddon.compatibility.nexo.objective.NexoFurnitureObjectiveFactory
import com.github.mrjimin.betonquestaddon.condition.LocationConditionFactory
import com.github.mrjimin.betonquestaddon.config.NotifyMessage
import com.github.mrjimin.betonquestaddon.util.action.Action
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
            register("nexoBlockAt", NexoSetBlockActionFactory())
            register("nexoFurnitureAt", NexoSetFurnitureActionFactory())
        }

        questRegistries.objective().apply {
            register("nexoBlockPlace", NexoBlockObjectiveFactory(Action.PLACE, NotifyMessage.BLOCK_PLACE))
            register("nexoBlockBreak", NexoBlockObjectiveFactory(Action.BREAK, NotifyMessage.BLOCK_BREAK))
            register("nexoBlockInteract", NexoBlockObjectiveFactory(Action.INTERACT, NotifyMessage.BLOCK_INTERACT))

            register("nexoFurniturePlace", NexoFurnitureObjectiveFactory(Action.PLACE, NotifyMessage.FURNITURE_PLACE))
            register("nexoFurnitureBreak", NexoFurnitureObjectiveFactory(Action.BREAK, NotifyMessage.FURNITURE_BREAK))
            register("nexoFurnitureInteract", NexoFurnitureObjectiveFactory(Action.INTERACT, NotifyMessage.FURNITURE_INTERACT))
        }
    }
}
