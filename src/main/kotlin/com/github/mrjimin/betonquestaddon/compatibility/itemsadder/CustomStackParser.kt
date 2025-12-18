package com.github.mrjimin.betonquestaddon.compatibility.itemsadder

import dev.lone.itemsadder.api.CustomStack
import org.betonquest.betonquest.api.instruction.argument.Argument
import org.betonquest.betonquest.api.quest.QuestException
import org.betonquest.betonquest.util.Utils

object CustomStackParser : Argument<CustomStack> {

    @Throws(QuestException::class)
    override fun apply(value: String?): CustomStack {
        return Utils.getNN(CustomStack.getInstance(value), "Invalid ItemsAdder Item: $value")
    }

}