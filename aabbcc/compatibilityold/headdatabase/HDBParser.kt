package com.github.mrjimin.betonquestaddon.compatibilityold.headdatabase

import com.github.mrjimin.betonquestaddon.util.server.NotFoundPlugin
import com.github.mrjimin.betonquestaddon.util.server.checkPlugin
import me.arcaniax.hdb.api.HeadDatabaseAPI
import org.betonquest.betonquest.api.quest.QuestException
import org.betonquest.betonquest.api.instruction.argument.Argument

object HDBParser : Argument<String> {

    init {
        if (!"HeadDatabase".checkPlugin()) throw NotFoundPlugin("HeadDatabase")
    }

    override fun apply(value: String): String = value.trim().takeIf { HeadDatabaseAPI().isHead(it) }
        ?: throw QuestException("Invalid or missing HeadDatabase Item ID: '$value'")
}