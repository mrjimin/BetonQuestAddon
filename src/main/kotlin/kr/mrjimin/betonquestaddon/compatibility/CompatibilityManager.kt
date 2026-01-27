package kr.mrjimin.betonquestaddon.compatibility

import kr.mrjimin.betonquestaddon.betonquest.BetonQuestIntegrator
import kr.mrjimin.betonquestaddon.compatibility.coinsengine.CoinsEngineIntegrator
import kr.mrjimin.betonquestaddon.compatibility.cosmeticscore.CosmeticsCoreIntegrator
import kr.mrjimin.betonquestaddon.compatibility.craftengine.CraftEngineIntegrator
import kr.mrjimin.betonquestaddon.compatibility.customcrops.CustomCropsIntegrator
import kr.mrjimin.betonquestaddon.compatibility.customfishing.CustomFishingIntegrator
import kr.mrjimin.betonquestaddon.compatibility.customnameplates.CustomNameplatesIntegrator
import kr.mrjimin.betonquestaddon.compatibility.hmccosmetics.HMCCosmeticsIntegrator
import kr.mrjimin.betonquestaddon.compatibility.itemsadder.ItemsAdderIntegrator
import kr.mrjimin.betonquestaddon.compatibility.nexo.NexoIntegrator
import kr.mrjimin.betonquestaddon.compatibility.typewriter.TypeWriterIntegrator
import kr.mrjimin.betonquestaddon.compatibility.worldguard.WorldGuardIntegrator
import kr.mrjimin.betonquestaddon.util.Logger
import org.betonquest.betonquest.api.BetonQuestApi
import org.bukkit.Bukkit
import org.bukkit.plugin.java.JavaPlugin

class CompatibilityManager(
    private val api: BetonQuestApi,
    private val plugin: JavaPlugin
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
        register("CoinsEngine") { CoinsEngineIntegrator() }
    }

    private fun register(name: String, factory: () -> ICompatibility) {
        if (name in integrators) return
        if (!plugin.config.getBoolean("hook.$name", true)) return

        val registerPlugin = Bukkit.getPluginManager().getPlugin(name)?.takeIf { it.isEnabled } ?: return
        integrators[name] = factory().apply { hook(api) }

        val version = registerPlugin.pluginMeta.version
        Logger.info("<green>Successfully hooked into <gray>$name <dark_gray>v$version")
    }
}
