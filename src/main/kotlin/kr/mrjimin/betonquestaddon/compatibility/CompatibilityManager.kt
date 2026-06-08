package kr.mrjimin.betonquestaddon.compatibility

import kr.mrjimin.betonquestaddon.betonquest.BetonQuestIntegrator
import kr.mrjimin.betonquestaddon.compatibility.cosmeticscore.CosmeticsCoreIntegrator
import kr.mrjimin.betonquestaddon.compatibility.craftengine.CraftEngineIntegrator
import kr.mrjimin.betonquestaddon.compatibility.customcrops.CustomCropsIntegrator
import kr.mrjimin.betonquestaddon.compatibility.customfishing.CustomFishingIntegrator
import kr.mrjimin.betonquestaddon.compatibility.customnameplates.CustomNameplatesIntegrator
import kr.mrjimin.betonquestaddon.compatibility.excellenteconomy.ExcellentEconomyIntegrator
import kr.mrjimin.betonquestaddon.compatibility.hmccosmetics.HMCCosmeticsIntegrator
import kr.mrjimin.betonquestaddon.compatibility.itemsadder.ItemsAdderIntegrator
import kr.mrjimin.betonquestaddon.compatibility.nexo.NexoIntegrator
import kr.mrjimin.betonquestaddon.compatibility.typewriter.TypeWriterIntegrator
import kr.mrjimin.betonquestaddon.compatibility.worldguard.WorldGuardIntegrator
import kr.mrjimin.betonquestaddon.util.Logger
import kr.mrjimin.betonquestaddon.util.getPluginVersion
import org.betonquest.betonquest.api.BetonQuestApi
import org.bukkit.plugin.java.JavaPlugin

class CompatibilityManager(
    private val plugin: JavaPlugin,
    private val api: BetonQuestApi
) {

    private val integrators = mutableMapOf<String, ICompatibility>()

    fun registerCompatiblePlugins() {
        BetonQuestIntegrator(api).hook()
        register("Nexo") { NexoIntegrator() }
        register("CraftEngine") { CraftEngineIntegrator() }
        register("ItemsAdder") { ItemsAdderIntegrator() }
        register("CustomCrops") { CustomCropsIntegrator() }
        register("CustomFishing") { CustomFishingIntegrator() }
        register("CustomNameplates") { CustomNameplatesIntegrator() }
        register("HMCCosmetics") { HMCCosmeticsIntegrator(plugin) }
        register("CosmeticsCore") { CosmeticsCoreIntegrator() }
        register("TypeWriter") { TypeWriterIntegrator() }
        register("WorldGuard") { WorldGuardIntegrator() }
        register("ExcellentEconomy") { ExcellentEconomyIntegrator() }
    }

    private fun register(name: String, factory: () -> ICompatibility) {
        if (name in integrators) return
        if (!plugin.config.getBoolean("hook.$name", true)) return

        val version = getPluginVersion(name) ?: run {
            Logger.debug("Skip hooking $name")
            return
        }

        integrators[name] = factory().apply { hook(api) }

        Logger.info("<green>Successfully hooked into <gray>$name <dark_gray>v$version")
    }
}
