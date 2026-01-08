package com.github.mrjimin.betonquestaddon.compatibility.nexo.action

import com.github.mrjimin.betonquestaddon.util.action.TargetType
import org.betonquest.betonquest.api.instruction.Instruction
import org.betonquest.betonquest.api.quest.action.PlayerAction
import org.betonquest.betonquest.api.quest.action.PlayerActionFactory

class NexoActionFactory(
    private val targetType: TargetType
) : PlayerActionFactory {

    override fun parsePlayer(instruction: Instruction): PlayerAction {
        val itemId = instruction.string().get()
        val location = instruction.location().get()

        val rotation = instruction.string().get("rotation", "NONE")
        val blockFace = instruction.string().get("blockFace", "SELF")

        return NexoAction(itemId, location, rotation, blockFace, targetType)
    }

}
