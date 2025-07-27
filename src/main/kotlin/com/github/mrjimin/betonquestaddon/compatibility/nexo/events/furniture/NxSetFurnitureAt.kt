package com.github.mrjimin.betonquestaddon.compatibility.nexo.events.furniture

import com.nexomc.nexo.api.NexoFurniture
import org.betonquest.betonquest.api.profile.Profile
import org.betonquest.betonquest.api.quest.QuestException
import org.betonquest.betonquest.api.quest.event.PlayerEvent
import org.betonquest.betonquest.instruction.variable.Variable
import org.bukkit.Location
import org.bukkit.Rotation
import org.bukkit.block.BlockFace

class NxSetFurnitureAt(
    private val itemID: Variable<String>,
    private val loc: Variable<Location>,
    private val rotation: Variable<Rotation>,
    private val blockFace: Variable<BlockFace>
) : PlayerEvent {

    override fun execute(profile: Profile) {
        val id = itemID.getValue(profile)
        val location = loc.getValue(profile)

        if (!NexoFurniture.isFurniture(id))
            throw QuestException("Nexo item is not a furniture: $id")

        val face = blockFace.getValue(profile)
            ?: throw QuestException("BlockFace must be provided.")

        val furnitureRotation = rotation.getValue(profile)
            ?: throw QuestException("Rotation must be provided.")

        NexoFurniture.place(id, location, furnitureRotation, face)
    }
}