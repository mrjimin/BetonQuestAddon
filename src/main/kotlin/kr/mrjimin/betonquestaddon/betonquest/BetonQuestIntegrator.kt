package kr.mrjimin.betonquestaddon.betonquest

import kr.mrjimin.betonquestaddon.betonquest.action.ParticleCubeActionFactory
import org.betonquest.betonquest.api.BetonQuestApi

class BetonQuestIntegrator(private val api: BetonQuestApi) {
    fun hook() {
        api.actions().registry().apply {
            register("particleCube", ParticleCubeActionFactory())
        }
    }
}