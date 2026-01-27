package kr.mrjimin.betonquestaddon.compatibility.typewriter

import kr.mrjimin.betonquestaddon.compatibility.ICompatibility
import kr.mrjimin.betonquestaddon.compatibility.typewriter.action.TWPlayCinematicActionFactory
import org.betonquest.betonquest.api.BetonQuestApi

class TypeWriterIntegrator : ICompatibility {
    override fun hook(api: BetonQuestApi) {
        val questRegistries = api.questRegistries

        questRegistries.action().apply {
            register("typeWriterPlayCinematic", TWPlayCinematicActionFactory())
        }
    }
}