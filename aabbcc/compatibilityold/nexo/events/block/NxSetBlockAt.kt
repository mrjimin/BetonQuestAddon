package com.github.mrjimin.betonquestaddon.compatibilityold.nexo.events.block

import com.nexomc.nexo.api.NexoBlocks
import org.betonquest.betonquest.api.profile.Profile
import org.betonquest.betonquest.api.quest.QuestException
import org.betonquest.betonquest.api.quest.event.PlayerEvent
import org.betonquest.betonquest.api.instruction.variable.Variable
import org.bukkit.Location

class NxSetBlockAt(
    private val itemID: Variable<String>,
    private val location: Variable<Location>
) : PlayerEvent {

    override fun execute(profile: Profile) {
        val loc = location.getValue(profile)
        val id = itemID.getValue(profile)

        if (!NexoBlocks.isCustomBlock(id))
            throw QuestException("Nexo item is not a block: $id")

        NexoBlocks.place(id, loc)
    }
}