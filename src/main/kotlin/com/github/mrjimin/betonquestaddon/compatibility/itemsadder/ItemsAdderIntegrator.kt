package com.github.mrjimin.betonquestaddon.compatibility.itemsadder

import com.github.mrjimin.betonquestaddon.compatibility.ICompatibility
import com.github.mrjimin.betonquestaddon.compatibility.itemsadder.event.ItemsAdderEventFactory
import com.github.mrjimin.betonquestaddon.compatibility.itemsadder.event.PlayAnimationFactory
import com.github.mrjimin.betonquestaddon.compatibility.itemsadder.item.ItemsAdderItemFactory
import com.github.mrjimin.betonquestaddon.compatibility.itemsadder.item.ItemsAdderQuestItemSerializer
import com.github.mrjimin.betonquestaddon.util.event.TargetType
import org.betonquest.betonquest.api.BetonQuestApi

class ItemsAdderIntegrator : ICompatibility {
    override fun hook(api: BetonQuestApi) {
        val data = api.primaryServerThreadData
        val questRegistries = api.questRegistries
        val loggerFactory = api.loggerFactory

        val itemRegistry = api.featureRegistries.item()
        itemRegistry.register("itemsAdder", ItemsAdderItemFactory())
        itemRegistry.registerSerializer("itemsAdder", ItemsAdderQuestItemSerializer())

        val event = questRegistries.event()
        event.register(
            "itemsAdderBlockAt",
            ItemsAdderEventFactory(data, TargetType.BLOCK)
        )
        event.register(
            "itemsAdderFurnitureAt",
            ItemsAdderEventFactory(data, TargetType.FURNITURE)
        )
        event.register(
            "itemsAdderPlayAnimation",
            PlayAnimationFactory(data, loggerFactory)
        )
    }
}