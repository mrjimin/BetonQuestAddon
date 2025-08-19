package com.github.mrjimin.betonquestaddon.util

import org.betonquest.betonquest.api.instruction.Instruction
import org.betonquest.betonquest.api.instruction.argument.Argument
import org.betonquest.betonquest.api.instruction.variable.Variable

fun Instruction.getBoolean(name: String, default: Boolean): Variable<Boolean> =
    this.getValue(name, Argument.BOOLEAN, default) ?: Variable(default)

fun Instruction.getNumberNotLessThanZero(name: String, default: Int = 1): Variable<Number> =
    this.getValue(name, Argument.NUMBER_NOT_LESS_THAN_ZERO, default) ?: Variable(default)

fun <T : Any> Instruction.getOrNull(arg: Argument<T>): Variable<T>? =
    try {
        get(arg)
    } catch (e: Exception) {
        null
    }
