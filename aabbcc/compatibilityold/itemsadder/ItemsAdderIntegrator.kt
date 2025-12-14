package com.github.mrjimin.betonquestaddon.compatibilityold.itemsadder

import com.github.mrjimin.betonquestaddon.betonquest.BetonQuestAddon
import com.github.mrjimin.betonquestaddon.compatibilityold.BQAddonIntegrator
import com.github.mrjimin.betonquestaddon.compatibilityold.itemsadder.conditions.IaBlockConditionFactory
import com.github.mrjimin.betonquestaddon.compatibilityold.itemsadder.events.animation.IaPlayAnimationEventFactory
import com.github.mrjimin.betonquestaddon.compatibilityold.itemsadder.events.block.IaSetBlockAtEventFactory
import com.github.mrjimin.betonquestaddon.compatibilityold.itemsadder.items.IaItemFactory
import com.github.mrjimin.betonquestaddon.compatibilityold.itemsadder.items.IaItemSerializer
import com.github.mrjimin.betonquestaddon.compatibilityold.itemsadder.objectives.block.IaBlockBreakObjectiveFactory
import com.github.mrjimin.betonquestaddon.compatibilityold.itemsadder.objectives.block.IaBlockInteractFactory
import com.github.mrjimin.betonquestaddon.compatibilityold.itemsadder.objectives.block.IaBlockPlaceObjectiveFactory

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