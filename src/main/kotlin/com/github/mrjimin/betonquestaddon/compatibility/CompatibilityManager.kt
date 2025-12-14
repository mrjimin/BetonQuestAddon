package com.github.mrjimin.betonquestaddon.compatibility

import com.github.mrjimin.betonquestaddon.compatibility.nexo.NexoIntegrator
import org.betonquest.betonquest.api.BetonQuestApi
import org.bukkit.Bukkit

class CompatibilityManager(private val api: BetonQuestApi) {

    private val integrators = mutableMapOf<String, ICompatibility>()

    fun registerCompatiblePlugins() {
        register("nexo", NexoIntegrator())
        // register("c", NexoIntegrator())
    }

    private fun register(name: String, compatibility: ICompatibility) {
        integrators[name] = compatibility
        if (Bukkit.getPluginManager().getPlugin(name)?.isEnabled == true) {
            compatibility.hook(api)
        }
    }

}
