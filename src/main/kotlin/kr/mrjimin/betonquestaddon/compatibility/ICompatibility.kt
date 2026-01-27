package kr.mrjimin.betonquestaddon.compatibility

import org.betonquest.betonquest.api.BetonQuestApi

interface ICompatibility {
    fun hook(api: BetonQuestApi)
}