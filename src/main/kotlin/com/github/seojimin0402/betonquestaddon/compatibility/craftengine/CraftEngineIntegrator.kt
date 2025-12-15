package com.github.seojimin0402.betonquestaddon.compatibility.craftengine

import com.github.seojimin0402.betonquestaddon.compatibility.ICompatibility
import com.github.seojimin0402.betonquestaddon.compatibility.craftengine.item.CraftEngineItemFactory
import com.github.seojimin0402.betonquestaddon.compatibility.craftengine.item.CraftEngineQuestItemSerializer
import org.betonquest.betonquest.api.BetonQuestApi

class CraftEngineIntegrator : ICompatibility {

    override fun hook(api: BetonQuestApi) {
        val itemRegistry = api.featureRegistries.item()
        itemRegistry.register("craftEngine", CraftEngineItemFactory())
        itemRegistry.registerSerializer("craftEngine", CraftEngineQuestItemSerializer())
    }

}