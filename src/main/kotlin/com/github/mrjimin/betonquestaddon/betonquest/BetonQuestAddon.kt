package com.github.mrjimin.betonquestaddon.betonquest

import com.github.mrjimin.betonquestaddon.compatibility.BQAddonIntegratorHandler
import org.betonquest.betonquest.BetonQuest
import org.betonquest.betonquest.api.kernel.TypeFactory
import org.betonquest.betonquest.api.logger.BetonQuestLogger
import org.betonquest.betonquest.api.profile.OnlineProfile
import org.betonquest.betonquest.api.quest.PrimaryServerThreadData
import org.betonquest.betonquest.item.QuestItemSerializer
import org.betonquest.betonquest.item.QuestItemWrapper
import org.bukkit.entity.Player

object BetonQuestAddon {

    lateinit var integratorHandler: BQAddonIntegratorHandler
        private set

    private val bqInstance: BetonQuest by lazy { BetonQuest.getInstance() }

    val logger: BetonQuestLogger by lazy { bqInstance.loggerFactory.create(this::class.java) }
    val server by lazy { bqInstance.server }
    val pluginMessage by lazy { bqInstance.pluginMessage }
    val variableProcessor by lazy { bqInstance.variableProcessor }
    val conversationColors by lazy { bqInstance.conversationColors }
    val featureRegistries by lazy { bqInstance.featureRegistries }
    val questRegistries by lazy { bqInstance.questRegistries }
    val data: PrimaryServerThreadData by lazy { PrimaryServerThreadData(server, server.scheduler, bqInstance) }

    val instance: BetonQuest
        get() = bqInstance

    fun initialize() {
        integratorHandler = BQAddonIntegratorHandler(
            logger,
//            data,
//            pluginMessage,
//            variableProcessor,
//            questRegistries
        )

        registerConditions()
        registerEvents()
        registerObjectives()
    }

    private fun registerConditions() {
    }

    private fun registerEvents() {
    }

    private fun registerObjectives() {
    }

    fun Player.toProfile(): OnlineProfile = instance.profileProvider.getProfile(this)

    fun registerItem(
        name: String,
        factory: TypeFactory<QuestItemWrapper>,
        serializer: QuestItemSerializer
    ) {
        val itemRegistry = featureRegistries.item()
        itemRegistry.register(name, factory)
        itemRegistry.registerSerializer(name, serializer)
    }
}
