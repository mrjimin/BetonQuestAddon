package com.github.mrjimin.betonquestaddon.compatibility.itemsadder

import com.github.mrjimin.betonquestaddon.compatibility.BQAddonIntegrator
import com.github.mrjimin.betonquestaddon.compatibility.itemsadder.conditions.IaBlockConditionFactory
import com.github.mrjimin.betonquestaddon.compatibility.itemsadder.events.animation.IaPlayAnimationEventFactory
import com.github.mrjimin.betonquestaddon.compatibility.itemsadder.events.block.IaSetBlockAtEventFactory
import com.github.mrjimin.betonquestaddon.compatibility.itemsadder.items.IaItemFactory
import com.github.mrjimin.betonquestaddon.compatibility.itemsadder.items.IaItemSerializer
import com.github.mrjimin.betonquestaddon.compatibility.itemsadder.objectives.IaBlockBreakObjectiveFactory
import com.github.mrjimin.betonquestaddon.compatibility.itemsadder.objectives.IaBlockPlaceObjectiveFactory

object ItemsAdderIntegrator : BQAddonIntegrator() {
    override fun hook() {
        condition.registerCombined("iaBlock", IaBlockConditionFactory(data))
        event.apply {
            register("iaBlockAt", IaSetBlockAtEventFactory(data))
            register("iaPlayAnimation", IaPlayAnimationEventFactory(loggerFactory, data))
        }
        objective.apply {
            register("iaBlockPlace", IaBlockPlaceObjectiveFactory(loggerFactory))
            register("iaBlockBreak", IaBlockBreakObjectiveFactory(loggerFactory))
        }
        registerItem("ia", IaItemFactory, IaItemSerializer)
    }

}