package com.github.mrjimin.betonquestaddon.compatibility.itemsadder

import com.github.mrjimin.betonquestaddon.betonquest.BetonQuestAddon
import com.github.mrjimin.betonquestaddon.compatibility.BQAddonIntegrator
import com.github.mrjimin.betonquestaddon.compatibility.itemsadder.conditions.IaBlockConditionFactory
import com.github.mrjimin.betonquestaddon.compatibility.itemsadder.events.animation.IaPlayAnimationEventFactory
import com.github.mrjimin.betonquestaddon.compatibility.itemsadder.events.block.IaSetBlockAtEventFactory
import com.github.mrjimin.betonquestaddon.compatibility.itemsadder.items.IaItemFactory
import com.github.mrjimin.betonquestaddon.compatibility.itemsadder.items.IaItemSerializer
import com.github.mrjimin.betonquestaddon.compatibility.itemsadder.objectives.block.IaBlockBreakObjectiveFactory
import com.github.mrjimin.betonquestaddon.compatibility.itemsadder.objectives.block.IaBlockInteractFactory
import com.github.mrjimin.betonquestaddon.compatibility.itemsadder.objectives.block.IaBlockPlaceObjectiveFactory

object ItemsAdderIntegrator : BQAddonIntegrator() {
    override fun hook() {
        condition.registerCombined("iaBlock", IaBlockConditionFactory(data))
        event.apply {
            register("iaBlockAt", IaSetBlockAtEventFactory(data))
            register("iaPlayAnimation", IaPlayAnimationEventFactory(data))
        }
        objective.apply {
            register("iaBlockPlace", IaBlockPlaceObjectiveFactory)
            register("iaBlockBreak", IaBlockBreakObjectiveFactory)
            register("iaBlockInteract", IaBlockInteractFactory)
        }
        BetonQuestAddon.registerItem("ia", IaItemFactory, IaItemSerializer)
    }

}