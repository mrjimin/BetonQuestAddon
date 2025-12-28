package com.github.mrjimin.betonquestaddon.compatibility.itemsadder

import com.github.mrjimin.betonquestaddon.compatibility.ICompatibility
import com.github.mrjimin.betonquestaddon.compatibility.itemsadder.event.ItemsAdderEventFactory
import com.github.mrjimin.betonquestaddon.compatibility.itemsadder.event.PlayAnimationFactory
import com.github.mrjimin.betonquestaddon.compatibility.itemsadder.item.ItemsAdderItemFactory
import com.github.mrjimin.betonquestaddon.compatibility.itemsadder.item.ItemsAdderQuestItemSerializer
import com.github.mrjimin.betonquestaddon.compatibility.itemsadder.objectives.ItemsAdderObjectiveFactory
import com.github.mrjimin.betonquestaddon.conditions.BaseConditionFactory
import com.github.mrjimin.betonquestaddon.util.event.ActionType
import com.github.mrjimin.betonquestaddon.util.event.TargetType
import dev.lone.itemsadder.api.CustomBlock
import dev.lone.itemsadder.api.CustomFurniture
import org.betonquest.betonquest.api.BetonQuestApi

class ItemsAdderIntegrator : ICompatibility {
    override fun hook(api: BetonQuestApi) {
        val questRegistries = api.questRegistries
        val loggerFactory = api.loggerFactory

        val itemRegistry = api.featureRegistries.item()
        itemRegistry.register("itemsAdder", ItemsAdderItemFactory())
        itemRegistry.registerSerializer("itemsAdder", ItemsAdderQuestItemSerializer())

        val condition = questRegistries.condition()
        condition.registerCombined(
            "itemsAdderBlock",
            BaseConditionFactory { location ->
                CustomBlock.byAlreadyPlaced(location.block)?.namespacedID
            }
        )
        condition.registerCombined(
            "itemsAdderFurniture",
            BaseConditionFactory { location ->
                CustomFurniture.byAlreadySpawned(location.block)?.namespacedID
            }
        )

        val event = questRegistries.event()
        event.register(
            "itemsAdderBlockAt",
            ItemsAdderEventFactory(TargetType.BLOCK)
        )
        event.register(
            "itemsAdderFurnitureAt",
            ItemsAdderEventFactory(TargetType.FURNITURE)
        )
        event.register(
            "itemsAdderPlayAnimation",
            PlayAnimationFactory(loggerFactory)
        )

        val objective = questRegistries.objective()
        objective.register("itemsAdderBlockBreak", ItemsAdderObjectiveFactory(TargetType.BLOCK, ActionType.BREAK))
        objective.register("itemsAdderBlockPlace", ItemsAdderObjectiveFactory(TargetType.BLOCK, ActionType.PLACE))
        objective.register("itemsAdderBlockInteract", ItemsAdderObjectiveFactory(TargetType.BLOCK, ActionType.INTERACT))

        objective.register("itemsAdderFurnitureBreak", ItemsAdderObjectiveFactory(TargetType.FURNITURE, ActionType.BREAK))
        objective.register("itemsAdderFurniturePlace", ItemsAdderObjectiveFactory(TargetType.FURNITURE, ActionType.PLACE))
        objective.register("itemsAdderFurnitureInteract", ItemsAdderObjectiveFactory(TargetType.FURNITURE, ActionType.INTERACT))
    }
}