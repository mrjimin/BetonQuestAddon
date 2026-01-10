package com.github.mrjimin.betonquestaddon.compatibility.itemsadder

import com.github.mrjimin.betonquestaddon.compatibility.ICompatibility
import com.github.mrjimin.betonquestaddon.compatibility.itemsadder.action.ItemsAdderEventFactory
import com.github.mrjimin.betonquestaddon.compatibility.itemsadder.action.ItemsAdderPlayAnimationFactory
import com.github.mrjimin.betonquestaddon.compatibility.itemsadder.item.ItemsAdderItemFactory
import com.github.mrjimin.betonquestaddon.compatibility.itemsadder.item.ItemsAdderQuestItemSerializer
import com.github.mrjimin.betonquestaddon.compatibility.itemsadder.objectives.ItemsAdderObjectiveFactory
import com.github.mrjimin.betonquestaddon.conditions.LocationConditionFactory
import com.github.mrjimin.betonquestaddon.util.action.ActionType
import com.github.mrjimin.betonquestaddon.util.action.TargetType
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
            register("itemsAdderBlockAt", ItemsAdderEventFactory(TargetType.BLOCK))
            register("itemsAdderFurnitureAt", ItemsAdderEventFactory(TargetType.FURNITURE))
            register("itemsAdderPlayAnimation", ItemsAdderPlayAnimationFactory(loggerFactory))
        }

        questRegistries.objective().apply {
            register("itemsAdderBlockPlace", ItemsAdderObjectiveFactory(ActionType.PLACE_BLOCK))
            register("itemsAdderBlockBreak", ItemsAdderObjectiveFactory(ActionType.BREAK_BLOCK))
            register("itemsAdderBlockInteract", ItemsAdderObjectiveFactory(ActionType.INTERACT_BLOCK))

            register("itemsAdderFurniturePlace", ItemsAdderObjectiveFactory(ActionType.PLACE_FURNITURE))
            register("itemsAdderFurnitureBreak", ItemsAdderObjectiveFactory(ActionType.BREAK_FURNITURE))
            register("itemsAdderFurnitureInteract", ItemsAdderObjectiveFactory(ActionType.INTERACT_FURNITURE))
        }
    }
}