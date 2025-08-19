package com.github.mrjimin.betonquestaddon.compatibility.craftengine

import com.github.mrjimin.betonquestaddon.hook.CraftEngineHook
import com.github.mrjimin.betonquestaddon.util.server.NotFoundPlugin
import com.github.mrjimin.betonquestaddon.util.server.checkPlugin
import org.betonquest.betonquest.api.instruction.argument.Argument
import org.betonquest.betonquest.api.quest.QuestException

object CeParser : Argument<String> {

    init {
        if (!"CraftEngine".checkPlugin()) throw NotFoundPlugin("CraftEngine")
    }

    override fun apply(value: String): String = value.trim().takeIf { CraftEngineHook.exists(it) }
        ?: throw QuestException("Invalid or missing CraftEngine Item ID: '$value'")
}