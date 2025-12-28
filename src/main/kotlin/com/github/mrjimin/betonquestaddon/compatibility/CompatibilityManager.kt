package com.github.mrjimin.betonquestaddon.compatibility

import com.github.mrjimin.betonquestaddon.compatibility.craftengine.CraftEngineIntegrator
import com.github.mrjimin.betonquestaddon.compatibility.itemsadder.ItemsAdderIntegrator
import com.github.mrjimin.betonquestaddon.compatibility.nexo.NexoIntegrator
import org.betonquest.betonquest.api.BetonQuestApi
import org.bukkit.Bukkit

class CompatibilityManager(private val api: BetonQuestApi) {

    private val integrators = mutableMapOf<String, ICompatibility>()

    fun registerCompatiblePlugins() {
        register("Nexo") { NexoIntegrator() }
        register("CraftEngine") { CraftEngineIntegrator() }
        register("ItemsAdder") { ItemsAdderIntegrator() }
    }

    private fun register(name: String, factory: () -> ICompatibility) {
        if (name in integrators) return

        Bukkit.getPluginManager().getPlugin(name)?.takeIf { it.isEnabled }?.let {
            val compatibility = factory()
            compatibility.hook(api)
            integrators[name] = compatibility
        }
    }

}
