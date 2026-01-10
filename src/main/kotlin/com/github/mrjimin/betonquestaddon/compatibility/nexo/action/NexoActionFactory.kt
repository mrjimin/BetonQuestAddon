package com.github.mrjimin.betonquestaddon.compatibility.nexo.action

import com.github.mrjimin.betonquestaddon.util.action.TargetType
import org.betonquest.betonquest.api.instruction.Instruction
import org.betonquest.betonquest.api.quest.action.PlayerAction
import org.betonquest.betonquest.api.quest.action.PlayerActionFactory
import org.bukkit.Rotation
import org.bukkit.block.BlockFace

class NexoActionFactory(
    private val targetType: TargetType
) : PlayerActionFactory {

    override fun parsePlayer(instruction: Instruction): PlayerAction {
        val itemId = instruction.string().get()
        val location = instruction.location().get()

        val rotation = instruction.enumeration(Rotation::class.java).get("rotation", Rotation.NONE)
        val blockFace = instruction.enumeration(BlockFace::class.java).get("blockFace", BlockFace.SELF)

        return NexoAction(itemId, location, rotation, blockFace, targetType)
    }

}
