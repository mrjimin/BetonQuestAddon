package com.github.mrjimin.betonquestaddon.compatibility.nexo

 import com.github.mrjimin.betonquestaddon.compatibility.BQAddonIntegrator
import com.github.mrjimin.betonquestaddon.compatibility.nexo.conditions.NxBlockConditionFactory
import com.github.mrjimin.betonquestaddon.compatibility.nexo.events.block.NxSetBlockAtEventFactory
import com.github.mrjimin.betonquestaddon.compatibility.nexo.events.furniture.NxSetFurnitureAtEventFactor
import com.github.mrjimin.betonquestaddon.compatibility.nexo.items.NxItemFactory
import com.github.mrjimin.betonquestaddon.compatibility.nexo.items.NxItemSerializer
import com.github.mrjimin.betonquestaddon.compatibility.nexo.objectives.block.NxBlockBreakObjectiveFactory
import com.github.mrjimin.betonquestaddon.compatibility.nexo.objectives.block.NxBlockInteractFactory
import com.github.mrjimin.betonquestaddon.compatibility.nexo.objectives.block.NxBlockPlaceObjectiveFactory
import com.github.mrjimin.betonquestaddon.compatibility.nexo.objectives.furniture.NxFurnitureBreakObjectiveFactory
import com.github.mrjimin.betonquestaddon.compatibility.nexo.objectives.furniture.NxFurnitureInteractObjectiveFactory
import com.github.mrjimin.betonquestaddon.compatibility.nexo.objectives.furniture.NxFurniturePlaceObjectiveFactory

object NexoIntegrator : BQAddonIntegrator() {
    override fun hook() {
        condition.registerCombined("nxBlock", NxBlockConditionFactory(data))
        event.apply {
            register("nxBlockAt", NxSetBlockAtEventFactory(data))
            register("nxFurnitureAt", NxSetFurnitureAtEventFactor(data))
        }
        objective.apply {
            register("nxBlockPlace", NxBlockPlaceObjectiveFactory(loggerFactory))
            register("nxBlockBreak", NxBlockBreakObjectiveFactory(loggerFactory))
            register("nxBlockInteract", NxBlockInteractFactory(loggerFactory))
            register("nxFurniturePlace", NxFurniturePlaceObjectiveFactory(loggerFactory))
            register("nxFurnitureBreak", NxFurnitureBreakObjectiveFactory(loggerFactory))
            register("nxFurnitureInteract", NxFurnitureInteractObjectiveFactory(loggerFactory))
        }
        registerItem("nexo", NxItemFactory, NxItemSerializer)
    }
}