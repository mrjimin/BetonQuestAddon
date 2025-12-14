package com.github.mrjimin.betonquestaddon.compatibility

import org.betonquest.betonquest.api.BetonQuestApi

interface ICompatibility {
    fun hook(api: BetonQuestApi)
}