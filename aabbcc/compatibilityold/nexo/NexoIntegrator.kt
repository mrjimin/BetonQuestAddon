package com.github.mrjimin.betonquestaddon.compatibilityold.nexo

import com.github.mrjimin.betonquestaddon.betonquest.BetonQuestAddon
import com.github.mrjimin.betonquestaddon.compatibilityold.BQAddonIntegrator
import com.github.mrjimin.betonquestaddon.compatibilityold.nexo.conditions.NxBlockConditionFactory
import com.github.mrjimin.betonquestaddon.compatibilityold.nexo.events.block.NxSetBlockAtEventFactory
import com.github.mrjimin.betonquestaddon.compatibilityold.nexo.events.furniture.NxSetFurnitureAtEventFactor
import com.github.mrjimin.betonquestaddon.compatibilityold.nexo.items.NxItemFactory
import com.github.mrjimin.betonquestaddon.compatibilityold.nexo.items.NxItemSerializer
import com.github.mrjimin.betonquestaddon.compatibilityold.nexo.objectives.block.NxBlockBreakObjectiveFactory
import com.github.mrjimin.betonquestaddon.compatibilityold.nexo.objectives.block.NxBlockInteractFactory
import com.github.mrjimin.betonquestaddon.compatibilityold.nexo.objectives.block.NxBlockPlaceObjectiveFactory
import com.github.mrjimin.betonquestaddon.compatibilityold.nexo.objectives.furniture.NxFurnitureBreakObjectiveFactory
import com.github.mrjimin.betonquestaddon.compatibilityold.nexo.objectives.furniture.NxFurnitureInteractObjectiveFactory
import com.github.mrjimin.betonquestaddon.compatibilityold.nexo.objectives.furniture.NxFurniturePlaceObjectiveFactory

object NexoIntegrator : BQAddonIntegrator() {
    override fun hook() {
        condition.registerCombined("nxBlock", NxBlockConditionFactory(data))
        event.apply {
            register("nxBlockAt", NxSetBlockAtEventFactory(data))
            register("nxFurnitureAt", NxSetFurnitureAtEventFactor(data))
        }
        objective.apply {
            register("nxBlockPlace", NxBlockPlaceObjectiveFactory)
            register("nxBlockBreak", NxBlockBreakObjectiveFactory)
            register("nxBlockInteract", NxBlockInteractFactory)

            register("nxFurniturePlace", NxFurniturePlaceObjectiveFactory)
            register("nxFurnitureBreak", NxFurnitureBreakObjectiveFactory)
            register("nxFurnitureInteract", NxFurnitureInteractObjectiveFactory)
        }
        BetonQuestAddon.registerItem("nexo", NxItemFactory, NxItemSerializer)
    }
}