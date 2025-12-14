package com.github.mrjimin.betonquestaddon.compatibilityold.headdatabase

import com.github.mrjimin.betonquestaddon.betonquest.BetonQuestAddon
import com.github.mrjimin.betonquestaddon.compatibilityold.BQAddonIntegrator
import com.github.mrjimin.betonquestaddon.compatibilityold.headdatabase.conditions.HDBBlockFactory
import com.github.mrjimin.betonquestaddon.compatibilityold.headdatabase.items.HDBItemFactory
import com.github.mrjimin.betonquestaddon.compatibilityold.headdatabase.items.HDBItemSerializer

object HDBIntegrator : BQAddonIntegrator() {
    override fun hook() {
        condition.registerCombined("hdbBlock", HDBBlockFactory(data))
        BetonQuestAddon.registerItem("hdb", HDBItemFactory, HDBItemSerializer)
    }
}