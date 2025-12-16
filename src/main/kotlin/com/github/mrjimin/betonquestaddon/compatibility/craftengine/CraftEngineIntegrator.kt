package com.github.mrjimin.betonquestaddon.compatibility.craftengine

import com.github.mrjimin.betonquestaddon.compatibility.ICompatibility
import com.github.mrjimin.betonquestaddon.compatibility.craftengine.item.CraftEngineItemFactory
import com.github.mrjimin.betonquestaddon.compatibility.craftengine.item.CraftEngineQuestItemSerializer
import com.github.mrjimin.betonquestaddon.conditions.CustomConditionFactory
import net.momirealms.craftengine.bukkit.api.BukkitAdaptors
import org.betonquest.betonquest.api.BetonQuestApi

class CraftEngineIntegrator : ICompatibility {

    override fun hook(api: BetonQuestApi) {
        val data = api.primaryServerThreadData
        val questRegistries = api.questRegistries

        val itemRegistry = api.featureRegistries.item()
        itemRegistry.register("craftEngine", CraftEngineItemFactory())
        itemRegistry.registerSerializer("craftEngine", CraftEngineQuestItemSerializer())

        val condition = questRegistries.condition()
        condition.registerCombined(
            "craftEngineBlock",
            CustomConditionFactory(data) {  location ->
                BukkitAdaptors.adapt(location.block).id().toString()
            }
        )
//        condition.registerCombined(
//            "craftFurniture",
//            CustomConditionFactory(data) { location ->
//                CraftEngineFurniture.
//                BukkitAdaptors.adapt(location.block).id().toString()
//            }
//        )
    }

}