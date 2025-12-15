package com.github.seojimin0402.betonquestaddon.compatibility.itemsadder

import dev.lone.itemsadder.api.CustomStack
import org.betonquest.betonquest.api.instruction.argument.Argument
import org.betonquest.betonquest.util.Utils

object ItemsAdderParser : Argument<CustomStack> {

    override fun apply(value: String): CustomStack =
        Utils.getNN(CustomStack.getInstance(value), "Invalid ItemsAdder Item: $value")
}