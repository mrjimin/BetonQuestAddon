package com.github.seojimin0402.betonquestaddon.compatibility.nexo

import com.github.seojimin0402.betonquestaddon.compatibility.ICompatibility
import com.github.seojimin0402.betonquestaddon.compatibility.nexo.conditions.NexoConditionFactory
import com.github.seojimin0402.betonquestaddon.compatibility.nexo.events.NexoEventFactory
import com.github.seojimin0402.betonquestaddon.compatibility.nexo.item.NexoItemFactory
import com.github.seojimin0402.betonquestaddon.compatibility.nexo.item.NexoQuestItemSerializer
import com.github.seojimin0402.betonquestaddon.compatibility.nexo.objectives.NexoObjectiveFactory
import com.github.seojimin0402.betonquestaddon.util.event.ActionType
import com.github.seojimin0402.betonquestaddon.util.event.TargetType
import com.nexomc.nexo.api.NexoBlocks
import com.nexomc.nexo.api.NexoFurniture
import org.betonquest.betonquest.api.BetonQuestApi

class NexoIntegrator : ICompatibility {

    override fun hook(api: BetonQuestApi) {
        val data = api.primaryServerThreadData
        val questRegistries = api.questRegistries

        val itemRegistry = api.featureRegistries.item()
        itemRegistry.register("nexo", NexoItemFactory())
        itemRegistry.registerSerializer("nexo", NexoQuestItemSerializer())

        val condition = questRegistries.condition()
        condition.registerCombined(
            "nexoBlock",
            NexoConditionFactory(data) { location ->
                NexoBlocks.customBlockMechanic(location)?.itemID
            }
        )
        condition.registerCombined(
            "nexoFurniture",
            NexoConditionFactory(data) { location ->
                NexoFurniture.furnitureMechanic(location)?.itemID
            }
        )

        val event = questRegistries.event()
        event.register(
            "nexoBlockAt",
            NexoEventFactory(data, TargetType.BLOCK)
        )
        event.register(
            "nexoFurnitureAt",
            NexoEventFactory(data, TargetType.FURNITURE)
        )

        val objective = questRegistries.objective()
        objective.register("nexoFurnitureBreak", NexoObjectiveFactory(ActionType.BREAK, TargetType.FURNITURE))
    }
}
