package com.github.mrjimin.betonquestaddon.compatibility

import com.github.mrjimin.betonquestaddon.betonquest.BetonQuestAddon
import com.github.mrjimin.betonquestaddon.betonquest.BetonQuestAddon.featureRegistries
import org.betonquest.betonquest.item.QuestItemSerializer
import org.betonquest.betonquest.item.QuestItemWrapper
import org.betonquest.betonquest.kernel.registry.TypeFactory
import org.betonquest.betonquest.kernel.registry.quest.ConditionTypeRegistry
import org.betonquest.betonquest.kernel.registry.quest.EventTypeRegistry
import org.betonquest.betonquest.kernel.registry.quest.ObjectiveTypeRegistry
import org.betonquest.betonquest.kernel.registry.quest.VariableTypeRegistry

abstract class BQAddonIntegrator {

    protected val loggerFactory = BetonQuestAddon.loggerFactory
    protected val data = BetonQuestAddon.data
    protected val pluginMessage = BetonQuestAddon.instance.pluginMessage
    protected val variableProcessor = BetonQuestAddon.instance.variableProcessor
    protected val registries = BetonQuestAddon.questRegistries

    protected val condition: ConditionTypeRegistry = registries.condition
    protected val event: EventTypeRegistry = registries.event
    protected val objective: ObjectiveTypeRegistry = registries.objective
    protected val variable: VariableTypeRegistry = registries.variable

    protected fun registerItem(
        name: String,
        factory: TypeFactory<QuestItemWrapper>,
        serializer: QuestItemSerializer
    ) {
        val itemRegistry = featureRegistries.item()
        itemRegistry.register(name, factory)
        itemRegistry.registerSerializer(name, serializer)
    }

    abstract fun hook()
}
