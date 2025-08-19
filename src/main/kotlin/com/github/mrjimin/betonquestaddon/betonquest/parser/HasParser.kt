package com.github.mrjimin.betonquestaddon.betonquest.parser

import org.betonquest.betonquest.api.quest.QuestException
import org.betonquest.betonquest.api.instruction.argument.Argument

object HasParser : Argument<String> {
    private val allowed = setOf("has", "!has", "any", "all", "none")

    override fun apply(value: String): String {
        val normalized = value.trim()
        if (allowed.contains(normalized)) return normalized
        throw QuestException("Invalid Has value: $value")
    }
}