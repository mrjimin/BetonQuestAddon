package kr.mrjimin.betonquestaddon.betonquest

import kr.mrjimin.betonquestaddon.BetonQuestAddonPlugin
import kr.mrjimin.betonquestaddon.betonquest.action.ParticleCubeActionFactory
import kr.mrjimin.betonquestaddon.betonquest.conversation.DialogConvIOFactory
import kr.mrjimin.betonquestaddon.betonquest.objective.smith.SmithObjectiveFactory
import kr.mrjimin.betonquestaddon.betonquest.objective.stonecut.StoneCutObjectiveFactory
import org.betonquest.betonquest.BetonQuest
import org.betonquest.betonquest.api.BetonQuestApi
import org.betonquest.betonquest.conversation.ConversationColors
import org.betonquest.betonquest.kernel.registry.feature.ConversationIORegistry

class BetonQuestIntegrator(
    private val plugin: BetonQuestAddonPlugin,
    private val api: BetonQuestApi
) {
    private val betonQuest: BetonQuest by lazy { BetonQuest.getInstance() }

    fun hook() {
        api.actions().registry().apply {
            register("particleCube", ParticleCubeActionFactory())
        }

        api.objectives().registry().apply {
            register("stoneCut", StoneCutObjectiveFactory())
            register("smith", SmithObjectiveFactory())
        }

        val componentLoader = betonQuest.componentLoader
        componentLoader.get(ConversationIORegistry::class.java).register(
            "dialog", DialogConvIOFactory(
                plugin.configsManager.dialog,
                componentLoader.get(ConversationColors::class.java)
            )
        )
    }
}