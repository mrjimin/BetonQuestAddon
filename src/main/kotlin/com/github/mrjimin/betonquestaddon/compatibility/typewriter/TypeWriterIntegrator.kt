package com.github.mrjimin.betonquestaddon.compatibility.typewriter

import com.github.mrjimin.betonquestaddon.compatibility.ICompatibility
import com.github.mrjimin.betonquestaddon.compatibility.typewriter.action.TWPlayCinematicActionFactory
import org.betonquest.betonquest.api.BetonQuestApi

class TypeWriterIntegrator : ICompatibility {
    override fun hook(api: BetonQuestApi) {
        val questRegistries = api.questRegistries

        questRegistries.action().apply {
            register("typeWriterPlayCinematic", TWPlayCinematicActionFactory())
        }
    }
}