package kr.mrjimin.betonquestaddon.betonquest

import kr.mrjimin.betonquestaddon.betonquest.action.ParticleCubeActionFactory
import kr.mrjimin.betonquestaddon.betonquest.conversation.DialogConvIOFactory
import kr.mrjimin.betonquestaddon.betonquest.objective.smith.SmithObjectiveFactory
import kr.mrjimin.betonquestaddon.betonquest.objective.stonecut.StoneCutObjectiveFactory
import org.betonquest.betonquest.api.BetonQuestApi
import org.betonquest.betonquest.kernel.registry.feature.ConversationIORegistry

class BetonQuestIntegrator(private val api: BetonQuestApi) {
    fun hook() {
        api.actions().registry().apply {
            register("particleCube", ParticleCubeActionFactory())
        }

        api.objectives().registry().apply {
            register("stoneCut", StoneCutObjectiveFactory())
            register("smith", SmithObjectiveFactory())
        }

    }
}