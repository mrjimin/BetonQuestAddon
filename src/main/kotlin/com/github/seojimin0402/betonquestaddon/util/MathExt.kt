package com.github.seojimin0402.betonquestaddon.util

import org.betonquest.betonquest.api.quest.QuestException

fun String.compareWith(op: String, other: String, ignoreCase: Boolean): Boolean {
    val opTrimmed = op.trim()

    val thisNum = this.toDoubleOrNull()
    val otherNum = other.toDoubleOrNull()
    if (thisNum != null && otherNum != null) {
        return thisNum.compareWith(opTrimmed, otherNum)
    }

    return when (opTrimmed) {
        "==" -> if (ignoreCase) this.equalsIgnoreCase(other) else this == other
        "!=" -> if (ignoreCase) !this.equalsIgnoreCase(other) else this != other
        else -> false
    }
}

fun <T : Comparable<T>> T.compareWith(op: String, other: T): Boolean {
    return when (op.trim()) {
        "==" -> this == other
        "!=" -> this != other
        ">=" -> this >= other
        "<=" -> this <= other
        ">"  -> this > other
        "<"  -> this < other
        else -> false
    }
}

fun String.equalsIgnoreCase(other: String): Boolean =
    this.equals(other, ignoreCase = true)

fun parseTypedValue(input: String): ParseResult {
    val regex = Regex("""\((string|number|boolean)\)(.+)""")
    val match = regex.matchEntire(input) ?: return ParseResult.Invalid

    val (type, raw) = match.destructured
    val value = raw.trim()

    return when(type) {
        "string" -> ParseResult.Str(value)
        "number" -> value.toDoubleOrNull()?.let {
            if (it % 1 == 0.0) ParseResult.Num(it.toInt()) else ParseResult.Num(it)
        } ?: ParseResult.Invalid
        "boolean" -> when(value.lowercase()) {
            "true" -> ParseResult.Bool(true)
            "false" -> ParseResult.Bool(false)
            else -> ParseResult.Invalid
        }
        else -> ParseResult.Invalid
    }
}

sealed class ParseResult {
    data class Str(val value: String): ParseResult()
    data class Num(val value: Number): ParseResult()
    data class Bool(val value: Boolean): ParseResult()
    object Invalid: ParseResult()
}

fun List<Boolean>.checkHas(type: String): Boolean {
    return when (type) {
        "has" -> this.firstOrNull() == true
        "!has" -> this.firstOrNull() == false
        "any" -> this.any { it }
        "all" -> this.all { it }
        "none" -> this.none { it }
        else -> throw QuestException("Unknown has type: $type")
    }
}