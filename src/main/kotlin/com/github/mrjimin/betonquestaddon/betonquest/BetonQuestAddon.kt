package com.github.mrjimin.betonquestaddon.betonquest

import com.github.mrjimin.betonquestaddon.betonquest.objectives.chat.ChatObjectiveFactory
import com.github.mrjimin.betonquestaddon.compatibility.BQAddonIntegratorHandler
import org.betonquest.betonquest.BetonQuest
import org.betonquest.betonquest.api.logger.BetonQuestLogger
import org.betonquest.betonquest.api.logger.BetonQuestLoggerFactory
import org.betonquest.betonquest.api.profile.OnlineProfile
import org.betonquest.betonquest.api.profile.Profile
import org.betonquest.betonquest.config.PluginMessage
import org.betonquest.betonquest.conversation.ConversationColors
import org.betonquest.betonquest.kernel.processor.quest.VariableProcessor
import org.betonquest.betonquest.kernel.registry.feature.FeatureRegistries
import org.betonquest.betonquest.kernel.registry.quest.QuestTypeRegistries
import org.betonquest.betonquest.quest.PrimaryServerThreadData
import org.bukkit.Server
import org.bukkit.entity.Player

object BetonQuestAddon {

    fun initialize() {
        BQAddonIntegratorHandler(
            loggerFactory.create(this::class.java),
            data,
            pluginMessage,
            variableProcessor,
            questRegistries
        )

        loadConditions()
        loadEvents()
        loadObjectives()
    }

    private fun loadConditions() {
    }

    private fun loadEvents() {
    }

    private fun loadObjectives() {
        questRegistries.objective.register("chat", ChatObjectiveFactory)
    }

    val instance: BetonQuest by lazy { BetonQuest.getInstance() }

    val loggerFactory: BetonQuestLoggerFactory
        get() = instance.loggerFactory

    val logger: BetonQuestLogger
        get() = loggerFactory.create(this::class.java)

    val server: Server
        get() = instance.server

    val pluginMessage: PluginMessage
        get() = instance.pluginMessage

    val variableProcessor: VariableProcessor
        get() = instance.variableProcessor

    val conversationColors: ConversationColors
        get() = instance.conversationColors

    val featureRegistries: FeatureRegistries
        get() = instance.featureRegistries

    val questRegistries: QuestTypeRegistries
        get() = instance.questRegistries

    val data: PrimaryServerThreadData by lazy { PrimaryServerThreadData(server, server.scheduler, instance) }

    fun Player.toProfile(): OnlineProfile = instance.profileProvider.getProfile(this)
}
