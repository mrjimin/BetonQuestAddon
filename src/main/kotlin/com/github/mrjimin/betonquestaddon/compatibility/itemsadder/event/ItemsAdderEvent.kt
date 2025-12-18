package com.github.mrjimin.betonquestaddon.compatibility.itemsadder.event

import com.github.mrjimin.betonquestaddon.util.event.TargetType
import dev.lone.itemsadder.api.CustomBlock
import dev.lone.itemsadder.api.CustomFurniture
import dev.lone.itemsadder.api.CustomStack
import org.betonquest.betonquest.api.instruction.variable.Variable
import org.betonquest.betonquest.api.profile.Profile
import org.betonquest.betonquest.api.quest.event.PlayerEvent
import org.bukkit.Location

class ItemsAdderEvent(
    private val customStack: Variable<CustomStack>,
    private val location: Variable<Location>,
    private val targetType: TargetType
) : PlayerEvent {

    override fun execute(profile: Profile) {
        val stack = customStack.getValue(profile)
        val loc = location.getValue(profile)

        when (targetType) {
            TargetType.BLOCK -> placeBlock(stack.namespacedID, loc)
            TargetType.FURNITURE -> placeFurniture(stack.namespacedID, loc)
        }
    }

    private fun placeBlock(id: String, loc: Location) {
        requireNotNull(CustomBlock.getInstance(id)) { "ItemsAdder item is not a block: $id" }
        CustomBlock.place(id, loc)
    }

    private fun placeFurniture(id: String, loc: Location) {
        requireNotNull(CustomFurniture.getInstance(id)) { "ItemsAdder item is not a furniture: $id" }
        CustomFurniture.spawn(id, loc.block)
    }
}