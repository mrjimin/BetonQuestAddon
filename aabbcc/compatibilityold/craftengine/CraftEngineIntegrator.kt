package com.github.mrjimin.betonquestaddon.compatibilityold.craftengine

import com.github.mrjimin.betonquestaddon.betonquest.BetonQuestAddon
import com.github.mrjimin.betonquestaddon.compatibilityold.BQAddonIntegrator
import com.github.mrjimin.betonquestaddon.compatibilityold.craftengine.conditions.CeBlockConditionFactory
import com.github.mrjimin.betonquestaddon.compatibilityold.craftengine.events.block.CeSetBlockAtEventFactory
import com.github.mrjimin.betonquestaddon.compatibilityold.craftengine.events.furniture.CeSetFurnitureAtEventFactor
import com.github.mrjimin.betonquestaddon.compatibilityold.craftengine.items.CeItemFactory
import com.github.mrjimin.betonquestaddon.compatibilityold.craftengine.items.CeItemSerializer
import com.github.mrjimin.betonquestaddon.compatibilityold.craftengine.objectives.block.CeBlockBreakObjectiveFactory
import com.github.mrjimin.betonquestaddon.compatibilityold.craftengine.objectives.block.CeBlockInteractObjectiveFactory
import com.github.mrjimin.betonquestaddon.compatibilityold.craftengine.objectives.block.CeBlockPlaceObjectiveFactory
import com.github.mrjimin.betonquestaddon.compatibilityold.craftengine.objectives.furniture.CeFurnitureBreakObjectiveFactory
import com.github.mrjimin.betonquestaddon.compatibilityold.craftengine.objectives.furniture.CeFurnitureInteractObjectiveFactory
import com.github.mrjimin.betonquestaddon.compatibilityold.craftengine.objectives.furniture.CeFurniturePlaceObjectiveFactory

object CraftEngineIntegrator : BQAddonIntegrator() {
    override fun hook() {
        condition.apply {
            registerCombined("ceBlock", CeBlockConditionFactory(data))
        }
        event.apply {
            register("ceBlockAt", CeSetBlockAtEventFactory(data))
            register("ceFurnitureAt", CeSetFurnitureAtEventFactor(data))
        }
        objective.apply {
            register("ceBlockPlace", CeBlockPlaceObjectiveFactory)
            register("ceBlockBreak", CeBlockBreakObjectiveFactory)
            register("ceBlockInteract", CeBlockInteractObjectiveFactory)
            register("ceFurniturePlace", CeFurniturePlaceObjectiveFactory)
            register("ceFurnitureBreak", CeFurnitureBreakObjectiveFactory)
            register("ceFurnitureInteract", CeFurnitureInteractObjectiveFactory)
        }
        BetonQuestAddon.registerItem("ce", CeItemFactory, CeItemSerializer)
    }
}