package com.github.mrjimin.betonquestaddon.compatibility.customfishing

import com.github.mrjimin.betonquestaddon.compatibility.ICompatibility
import com.github.mrjimin.betonquestaddon.compatibility.customfishing.item.CFishingItemFactory
import com.github.mrjimin.betonquestaddon.compatibility.customfishing.item.CFishingQuestItemSerializer
import com.github.mrjimin.betonquestaddon.compatibility.customfishing.objective.ActivateTotemObjectiveFactory
import com.github.mrjimin.betonquestaddon.compatibility.customfishing.objective.CaughtFishObjectiveFactory
import com.github.mrjimin.betonquestaddon.config.NotifyMessage
import net.momirealms.customfishing.api.BukkitCustomFishingPlugin
import org.betonquest.betonquest.api.BetonQuestApi

class CustomFishingIntegrator : ICompatibility {
    private val itemManager = BukkitCustomFishingPlugin.getInstance().itemManager

    override fun hook(api: BetonQuestApi) {
        val questRegistries = api.questRegistries

        api.featureRegistries.item().apply {
            register("customFishing", CFishingItemFactory(itemManager))
            registerSerializer("customFishing", CFishingQuestItemSerializer(itemManager))
        }

        questRegistries.objective().apply {
            register("customFishingCaughtFish", CaughtFishObjectiveFactory(FishingCaughtType.FISH, NotifyMessage.CUSTOM_FISHING_CAUGHT_FISH))
            register("customFishingCaughtGroup", CaughtFishObjectiveFactory(FishingCaughtType.GROUP, NotifyMessage.CUSTOM_FISHING_CAUGHT_GROUP))
            register("customFishingActivateTotem", ActivateTotemObjectiveFactory())
        }
    }
}