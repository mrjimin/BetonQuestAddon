package com.github.mrjimin.betonquestaddon.compatibility.itemsadder.action

import dev.lone.itemsadder.api.CustomFurniture
import org.betonquest.betonquest.api.QuestException
import org.betonquest.betonquest.api.instruction.Argument
import org.betonquest.betonquest.api.profile.Profile
import org.betonquest.betonquest.api.quest.action.PlayerAction
import org.bukkit.Location

class ItemsAdderSetFurnitureAction(
    private val itemId: Argument<String>,
    private val location: Argument<Location>
) : PlayerAction {

    override fun execute(profile: Profile) {
        val id = itemId.getValue(profile)

        id.takeIf { CustomFurniture.getInstance(id) != null }
            ?: throw QuestException("ItemsAdder item is not a furniture: $id")

        CustomFurniture.spawn(id, location.getValue(profile).block)
    }

    override fun isPrimaryThreadEnforced(): Boolean {
        return true
    }
}