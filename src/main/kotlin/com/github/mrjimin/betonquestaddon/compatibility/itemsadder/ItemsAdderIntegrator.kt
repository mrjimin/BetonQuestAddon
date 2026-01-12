package com.github.mrjimin.betonquestaddon.compatibility.itemsadder

import com.github.mrjimin.betonquestaddon.betonquest.condition.LocationConditionFactory
import com.github.mrjimin.betonquestaddon.compatibility.ICompatibility
import com.github.mrjimin.betonquestaddon.compatibility.itemsadder.action.ItemsAdderPlayAnimationFactory
import com.github.mrjimin.betonquestaddon.compatibility.itemsadder.action.ItemsAdderSetBlockActionFactory
import com.github.mrjimin.betonquestaddon.compatibility.itemsadder.action.ItemsAdderSetFurnitureActionFactory
import com.github.mrjimin.betonquestaddon.compatibility.itemsadder.item.ItemsAdderItemFactory
import com.github.mrjimin.betonquestaddon.compatibility.itemsadder.item.ItemsAdderQuestItemSerializer
import com.github.mrjimin.betonquestaddon.compatibility.itemsadder.objective.ItemsAdderBlockObjectiveFactory
import com.github.mrjimin.betonquestaddon.compatibility.itemsadder.objective.ItemsAdderFurnitureObjectiveFactory
import com.github.mrjimin.betonquestaddon.config.NotifyMessage
import com.github.mrjimin.betonquestaddon.util.action.Action
import dev.lone.itemsadder.api.CustomBlock
import dev.lone.itemsadder.api.CustomFurniture
import org.betonquest.betonquest.api.BetonQuestApi

class ItemsAdderIntegrator : ICompatibility {
    override fun hook(api: BetonQuestApi) {
        val questRegistries = api.questRegistries
        val loggerFactory = api.loggerFactory

        api.featureRegistries.item().apply {
            register("itemsAdder", ItemsAdderItemFactory())
            registerSerializer("itemsAdder", ItemsAdderQuestItemSerializer())
        }

        questRegistries.condition().apply {
            registerCombined("itemsAdderBlock", LocationConditionFactory { location ->
                    CustomBlock.byAlreadyPlaced(location.block)?.namespacedID
                }
            )
            registerCombined("itemsAdderFurniture", LocationConditionFactory { location ->
                    CustomFurniture.byAlreadySpawned(location.block)?.namespacedID
                }
            )
        }

        questRegistries.action().apply {
            register("itemsAdderBlockAt", ItemsAdderSetBlockActionFactory())
            register("itemsAdderFurnitureAt", ItemsAdderSetFurnitureActionFactory())
            register("itemsAdderPlayAnimation", ItemsAdderPlayAnimationFactory(loggerFactory))
        }

        questRegistries.objective().apply {
            register("itemsAdderBlockPlace", ItemsAdderBlockObjectiveFactory(Action.PLACE, NotifyMessage.BLOCK_PLACE))
            register("itemsAdderBlockBreak", ItemsAdderBlockObjectiveFactory(Action.BREAK, NotifyMessage.BLOCK_BREAK))
            register("itemsAdderBlockInteract", ItemsAdderBlockObjectiveFactory(Action.INTERACT, NotifyMessage.BLOCK_INTERACT))

            register("itemsAdderFurniturePlace", ItemsAdderFurnitureObjectiveFactory(Action.PLACE, NotifyMessage.FURNITURE_PLACE))
            register("itemsAdderFurnitureBreak", ItemsAdderFurnitureObjectiveFactory(Action.BREAK, NotifyMessage.FURNITURE_BREAK))
            register("itemsAdderFurnitureInteract", ItemsAdderFurnitureObjectiveFactory(Action.INTERACT, NotifyMessage.FURNITURE_INTERACT))
        }
    }
}