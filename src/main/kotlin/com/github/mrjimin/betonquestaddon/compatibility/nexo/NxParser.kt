package com.github.mrjimin.betonquestaddon.compatibility.nexo

import com.github.mrjimin.betonquestaddon.util.server.NotFoundPlugin
import com.github.mrjimin.betonquestaddon.util.server.checkPlugin
import com.nexomc.nexo.api.NexoItems
import org.betonquest.betonquest.api.instruction.argument.Argument
import org.betonquest.betonquest.api.quest.QuestException

object NxParser : Argument<String> {

    init {
        if (!"Nexo".checkPlugin()) throw NotFoundPlugin("Nexo")
    }

    override fun apply(value: String): String = value.trim().takeIf { NexoItems.exists(it) }
        ?: throw QuestException("Invalid or missing Nexo Item ID: '$value'")
}