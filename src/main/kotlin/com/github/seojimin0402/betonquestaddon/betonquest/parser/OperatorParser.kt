package com.github.seojimin0402.betonquestaddon.betonquest.parser

import org.betonquest.betonquest.api.quest.QuestException
import org.betonquest.betonquest.api.instruction.argument.Argument

object OperatorParser : Argument<String> {
    private val allowed = setOf("==", "!=", ">=", "<=", ">", "<")

    override fun apply(value: String): String {
        val normalized = value.trim()
        if (allowed.contains(normalized)) return normalized
        throw QuestException("Invalid Operator value: $value")
    }
}
