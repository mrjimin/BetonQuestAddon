package com.github.mrjimin.betonquestaddon.compatibility.nexo.action

import com.github.mrjimin.betonquestaddon.util.action.TargetType
import com.nexomc.nexo.api.NexoBlocks
import com.nexomc.nexo.api.NexoFurniture
import org.betonquest.betonquest.api.instruction.Argument
import org.betonquest.betonquest.api.profile.Profile
import org.betonquest.betonquest.api.quest.action.PlayerAction
import org.bukkit.Location
import org.bukkit.Rotation
import org.bukkit.block.BlockFace

class NexoAction(
    private val itemId: Argument<String>,
    private val location: Argument<Location>,
    private val rotation: Argument<String>?,
    private val blockFace: Argument<String>?,
    private val targetType: TargetType
) : PlayerAction {

    override fun execute(profile: Profile) {
        val id = itemId.getValue(profile)
        val loc = location.getValue(profile)

        when (targetType) {
            TargetType.BLOCK -> placeBlock(id, loc)
            TargetType.FURNITURE -> placeFurniture(id, loc, profile)
        }
    }

    private fun placeBlock(id: String, loc: Location) {
        requireNotNull(NexoBlocks.isCustomBlock(id)) { "Nexo item is not a block: $id" }
        NexoBlocks.place(id, loc)
    }

    private fun placeFurniture(id: String, loc: Location, profile: Profile) {
        requireNotNull(NexoFurniture.isFurniture(id)) { "Nexo item is not a furniture: $id" }
        NexoFurniture.place(id, loc, rotation(profile), blockFace(profile))
    }

    private fun rotation(profile: Profile): Rotation =
        rotation
            ?.getValue(profile)
            ?.uppercase()
            ?.let { Rotation.valueOf(it) }
            ?: Rotation.NONE

    private fun blockFace(profile: Profile): BlockFace =
        blockFace
            ?.getValue(profile)
            ?.uppercase()
            ?.let { BlockFace.valueOf(it) }
            ?: BlockFace.SELF

    override fun isPrimaryThreadEnforced(): Boolean {
        return true
    }
}
