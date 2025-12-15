package com.github.seojimin0402.betonquestaddon.betonquest.parser

import org.betonquest.betonquest.api.instruction.argument.Argument

object StringListParser : Argument<Set<String>> {
    override fun apply(value: String): Set<String> =
        value.split(",")
            .map { it.trim().lowercase() }
            .filter { it.isNotEmpty() }
            .toSet()
}
