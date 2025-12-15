package com.github.seojimin0402.betonquestaddon.compatibility.nexo

import com.github.seojimin0402.betonquestaddon.compatibility.ICompatibility
import com.github.seojimin0402.betonquestaddon.compatibility.nexo.conditions.NexoBlockCondition
import com.github.seojimin0402.betonquestaddon.compatibility.nexo.conditions.NexoBlockConditionFactory
import com.github.seojimin0402.betonquestaddon.compatibility.nexo.conditions.NexoMechanicConditionFactory
import com.github.seojimin0402.betonquestaddon.compatibility.nexo.item.NexoItemFactory
import com.github.seojimin0402.betonquestaddon.compatibility.nexo.item.NexoQuestItemSerializer
import com.nexomc.nexo.api.NexoBlocks
import com.nexomc.nexo.api.NexoFurniture
import org.betonquest.betonquest.api.BetonQuestApi

class NexoIntegrator : ICompatibility {

    override fun hook(api: BetonQuestApi) {
        val loggerFactory = api.loggerFactory
        val data = api.primaryServerThreadData
        val questRegistries = api.questRegistries

        val itemRegistry = api.featureRegistries.item()
        itemRegistry.register("nexo", NexoItemFactory())
        itemRegistry.registerSerializer("nexo", NexoQuestItemSerializer())

        val condition = questRegistries.condition()
        condition.registerCombined("nexoBlockAt",
            NexoMechanicConditionFactory(data) { location ->
                NexoBlocks.customBlockMechanic(location)
                    ?.factory
                    ?.mechanicID
            }
        )
        condition.registerCombined("nexoFurnitureAt",
            NexoMechanicConditionFactory(data) { location ->
                NexoFurniture.furnitureMechanic(location)
                    ?.factory
                    ?.mechanicID
            }
        )
    }
}
