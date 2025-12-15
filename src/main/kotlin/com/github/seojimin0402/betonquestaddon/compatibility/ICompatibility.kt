package com.github.seojimin0402.betonquestaddon.compatibility

import org.betonquest.betonquest.api.BetonQuestApi

interface ICompatibility {
    fun hook(api: BetonQuestApi)
}