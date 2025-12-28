//package com.github.mrjimin.betonquestaddon.util
//
//import org.betonquest.betonquest.api.instruction.Instruction
//import org.betonquest.betonquest.api.instruction.Argument
//import org.betonquest.betonquest.api.instruction.Argument
//
//fun Instruction.getBoolean(name: String, default: Boolean): Argument<Boolean> =
//    this.getValue(name, Argument.BOOLEAN, default) ?: Argument(default)
//
//fun Instruction.getNumberNotLessThanZero(name: String, default: Int = 1): Argument<Number> =
//    this.getValue(name, Argument.NUMBER_NOT_LESS_THAN_ZERO, default) ?: Argument(default)
//
//fun <T : Any> Instruction.getOrNull(arg: Argument<T>): Argument<T>? =
//    runCatching { get(arg) }.getOrNull()
