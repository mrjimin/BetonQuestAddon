package com.github.mrjimin.betonquestaddon.util

import org.betonquest.betonquest.instruction.Instruction
import org.betonquest.betonquest.instruction.argument.Argument
import org.betonquest.betonquest.instruction.variable.Variable

fun Instruction.getBoolean(name: String, default: Boolean): Variable<Boolean> =
    this.getValue(name, Argument.BOOLEAN, default) ?: Variable(default)

fun Instruction.getNumberNotLessThanOne(name: String, default: Int = 1): Variable<Number> =
    this.getValue(name, Argument.NUMBER_NOT_LESS_THAN_ONE, default) ?: Variable(default)
