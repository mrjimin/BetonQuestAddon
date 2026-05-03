package kr.mrjimin.betonquestaddon.compatibility.excellenteconomy

import kr.mrjimin.betonquestaddon.compatibility.ICompatibility
import kr.mrjimin.betonquestaddon.compatibility.excellenteconomy.action.ExcellentEconomyActionFactory
import kr.mrjimin.betonquestaddon.compatibility.excellenteconomy.condition.ExcellentEconomyConditionFactory
import org.betonquest.betonquest.api.BetonQuestApi
import org.bukkit.Bukkit
import su.nightexpress.excellenteconomy.api.ExcellentEconomyAPI

class ExcellentEconomyIntegrator : ICompatibility {
    override fun hook(api: BetonQuestApi) {

        val excellentEconomyApi = Bukkit.getServer().servicesManager.getRegistration(ExcellentEconomyAPI::class.java)?.provider
            ?: return

        api.actions().registry().apply {
            register("excellentEconomy", ExcellentEconomyActionFactory(excellentEconomyApi))
        }

        api.conditions().registry().apply {
            register("excellentEconomy", ExcellentEconomyConditionFactory(excellentEconomyApi))
        }
    }
}