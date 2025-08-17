package com.github.mrjimin.betonquestaddon.test

import com.github.mrjimin.betonquestaddon.BetonQuestAddonPlugin
import com.github.mrjimin.betonquestaddon.test.listener.TestListener
import com.github.mrjimin.betonquestaddon.test.listener.ae.AEListener
import com.github.mrjimin.betonquestaddon.test.listener.lands.LandsListener

class TestPluginInit(
    private val plugin: BetonQuestAddonPlugin
) {

    init {
        registerEvents()
        plugin.logger.info { "TestPlugin initialized" }
    }

    fun registerEvents() {
        // plugin.server.pluginManager.registerEvents(TestListener(), plugin)
        // plugin.server.pluginManager.registerEvents(AEListener(), plugin)
        // plugin.server.pluginManager.registerEvents(LandsListener(), plugin)
        // plugin.server.pluginManager.registerEvents(AdvancementListener(), plugin)
    }
}