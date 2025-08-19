package com.github.mrjimin.betonquestaddon.betonquest.parser

import com.github.mrjimin.betonquestaddon.api.BQAddonItems
import org.betonquest.betonquest.api.instruction.argument.Argument
import org.betonquest.betonquest.api.quest.QuestException

object BQAddonParser : Argument<String> {

    override fun apply(value: String): String = value.trim().takeIf { BQAddonItems.exists(it) }
        ?: throw QuestException("Invalid or missing BetonQuestAddon Item ID: '$value'")
}