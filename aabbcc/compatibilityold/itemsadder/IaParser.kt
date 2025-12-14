package com.github.mrjimin.betonquestaddon.compatibilityold.itemsadder

import com.github.mrjimin.betonquestaddon.util.server.NotFoundPlugin
import com.github.mrjimin.betonquestaddon.util.server.checkPlugin
import dev.lone.itemsadder.api.CustomStack
import org.betonquest.betonquest.api.quest.QuestException
import org.betonquest.betonquest.api.instruction.argument.Argument

object IaParser : Argument<CustomStack> {

    init {
        if (!"ItemsAdder".checkPlugin()) throw NotFoundPlugin("ItemsAdder")
    }

    override fun apply(value: String): CustomStack = value.trim().let { CustomStack.getInstance(it) }
            ?: throw QuestException("Invalid or missing ItemsAdder Item ID: '$value'")
}