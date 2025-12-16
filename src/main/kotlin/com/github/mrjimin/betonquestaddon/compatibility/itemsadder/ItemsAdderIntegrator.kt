package com.github.mrjimin.betonquestaddon.compatibility.itemsadder

import com.github.mrjimin.betonquestaddon.compatibility.ICompatibility
import com.github.mrjimin.betonquestaddon.compatibility.itemsadder.item.ItemsAdderItemFactory
import com.github.mrjimin.betonquestaddon.compatibility.itemsadder.item.ItemsAdderQuestItemSerializer
import org.betonquest.betonquest.api.BetonQuestApi

class ItemsAdderIntegrator : ICompatibility {
    override fun hook(api: BetonQuestApi) {
        val itemRegistry = api.featureRegistries.item()
        itemRegistry.register("itemsAdder", ItemsAdderItemFactory())
        itemRegistry.registerSerializer("itemsAdder", ItemsAdderQuestItemSerializer())
    }
}