package com.github.mrjimin.betonquestaddon.compatibilityold.itemsadder.events.block

import dev.lone.itemsadder.api.CustomBlock
import dev.lone.itemsadder.api.CustomStack
import org.betonquest.betonquest.api.profile.Profile
import org.betonquest.betonquest.api.quest.QuestException
import org.betonquest.betonquest.api.quest.event.PlayerEvent
import org.betonquest.betonquest.api.instruction.variable.Variable
import org.bukkit.Location

class IaSetBlockAt(
    private val itemID: Variable<CustomStack>,
    private val location: Variable<Location>
) : PlayerEvent {

    override fun execute(profile: Profile) {
        val loc = location.getValue(profile)
        val customStack = itemID.getValue(profile)

        if (!customStack.isBlock)
            throw QuestException("ItemsAdder Item is not a block: $itemID")

        CustomBlock.place(customStack.namespacedID, loc)
    }
}