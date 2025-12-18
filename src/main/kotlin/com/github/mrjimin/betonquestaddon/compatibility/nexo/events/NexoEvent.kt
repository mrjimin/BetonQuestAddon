package com.github.mrjimin.betonquestaddon.compatibility.nexo.events

import com.github.mrjimin.betonquestaddon.util.event.TargetType
import com.nexomc.nexo.api.NexoBlocks
import com.nexomc.nexo.api.NexoFurniture
import org.betonquest.betonquest.api.instruction.variable.Variable
import org.betonquest.betonquest.api.profile.Profile
import org.betonquest.betonquest.api.quest.event.PlayerEvent
import org.bukkit.Location
import org.bukkit.Rotation
import org.bukkit.block.BlockFace

class NexoEvent(
    private val itemId: Variable<String>,
    private val location: Variable<Location>,
    private val rotation: Variable<String>?,
    private val blockFace: Variable<String>?,
    private val targetType: TargetType
) : PlayerEvent {

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
}
