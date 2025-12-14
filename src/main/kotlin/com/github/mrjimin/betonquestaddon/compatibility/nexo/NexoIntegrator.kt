package com.github.mrjimin.betonquestaddon.compatibility.nexo

import com.github.mrjimin.betonquestaddon.compatibility.ICompatibility
import com.github.mrjimin.betonquestaddon.compatibility.nexo.item.NexoItemFactory
import com.github.mrjimin.betonquestaddon.compatibility.nexo.item.NexoQuestItemSerializer
import org.betonquest.betonquest.api.BetonQuestApi

class NexoIntegrator : ICompatibility {

    override fun hook(api: BetonQuestApi) {
//        val loggerFactory = api.loggerFactory
//        val data = api.primaryServerThreadData
//        val questRegistries = api.questRegistries

        val itemRegistry = api.featureRegistries.item()
        itemRegistry.register("nexo", NexoItemFactory())
        itemRegistry.registerSerializer("nexo", NexoQuestItemSerializer())
    }
}
