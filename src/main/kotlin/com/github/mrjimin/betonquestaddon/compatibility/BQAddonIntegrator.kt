package com.github.mrjimin.betonquestaddon.compatibility

import com.github.mrjimin.betonquestaddon.betonquest.BetonQuestAddon

abstract class BQAddonIntegrator {
    protected val logger = BetonQuestAddon.logger
    protected val data = BetonQuestAddon.data
    protected val pluginMessage = BetonQuestAddon.instance.pluginMessage
    protected val variableProcessor = BetonQuestAddon.instance.variableProcessor

    private val registries get() = BetonQuestAddon.questRegistries
    protected val condition get() = registries.condition()
    protected val event get() = registries.event()
    protected val objective get() = registries.objective()
    protected val variable get() = registries.variable()

    abstract fun hook()
}
