package com.github.mrjimin.betonquestaddon.betonquest.objectives.chat

import com.github.mrjimin.betonquestaddon.betonquest.parser.VariableParser
import com.github.mrjimin.betonquestaddon.config.Settings
import org.betonquest.betonquest.api.Objective
import org.betonquest.betonquest.instruction.Instruction
import org.betonquest.betonquest.kernel.registry.TypeFactory

object ChatObjectiveFactory : TypeFactory<Objective> {

    override fun parseInstruction(instruction: Instruction): Objective {
        val cancel = instruction.hasArgument(Settings.OBJ_CHAT.toString())
        val variable = instruction.getValue("variable", VariableParser)
        return ChatObjective(instruction, cancel, variable)
    }
}