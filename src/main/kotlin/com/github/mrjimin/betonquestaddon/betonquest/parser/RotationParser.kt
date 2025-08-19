package com.github.mrjimin.betonquestaddon.betonquest.parser

import org.betonquest.betonquest.api.quest.QuestException
import org.betonquest.betonquest.api.instruction.argument.Argument
import org.bukkit.Rotation

object RotationParser : Argument<Rotation> {
    override fun apply(value: String): Rotation {
        return Rotation.entries.find { it.name.equals(value, ignoreCase = true) }
            ?: throw QuestException("Invalid Rotation value: '$value'. Valid values are: ${Rotation.entries.joinToString(", ") { it.name }}")
    }
}
