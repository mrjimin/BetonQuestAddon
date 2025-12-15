package com.github.seojimin0402.betonquestaddon.compatibility

import com.github.seojimin0402.betonquestaddon.compatibility.nexo.NexoIntegrator
import org.betonquest.betonquest.api.BetonQuestApi
import org.bukkit.Bukkit

class CompatibilityManager(private val api: BetonQuestApi) {

    private val integrators = mutableMapOf<String, ICompatibility>()

    fun registerCompatiblePlugins() {
        register("Nexo", NexoIntegrator())
        register("CraftEngine", NexoIntegrator())
    }

    private fun register(name: String, compatibility: ICompatibility) {
        integrators[name] = compatibility
        if (Bukkit.getPluginManager().getPlugin(name)?.isEnabled == true) {
            compatibility.hook(api)
        }
    }

}
