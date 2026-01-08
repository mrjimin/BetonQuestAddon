package com.github.mrjimin.betonquestaddon.compatibility.craftengine.action

import com.github.mrjimin.betonquestaddon.compatibility.craftengine.asCraftKey
import com.github.mrjimin.betonquestaddon.util.action.TargetType
import net.momirealms.craftengine.bukkit.api.CraftEngineBlocks
import net.momirealms.craftengine.bukkit.api.CraftEngineFurniture
import org.betonquest.betonquest.api.instruction.Argument
import org.betonquest.betonquest.api.profile.Profile
import org.betonquest.betonquest.api.quest.action.PlayerAction
import org.bukkit.Location

class CraftEngineAction(
    private val itemId: Argument<String>,
    private val location: Argument<Location>,
    private val playSound: Argument<Boolean>,
    private val targetType: TargetType
) : PlayerAction {

    override fun execute(profile: Profile) {
        val id = itemId.getValue(profile)
        val loc = location.getValue(profile)

        val playSound = playSound.getValue(profile)

        when (targetType) {
            TargetType.BLOCK -> placeBlock(id, loc, playSound)
            TargetType.FURNITURE -> placeFurniture(id, loc, playSound)
        }
    }

    private fun placeBlock(id: String, loc: Location, playSound: Boolean) {
        requireNotNull(CraftEngineBlocks.byId(id.asCraftKey())) { "CraftEngine item is not a block: $id" }
        CraftEngineBlocks.place(loc, id.asCraftKey(), playSound)
    }

    private fun placeFurniture(id: String, loc: Location, playSound: Boolean) {
        val furniture = CraftEngineFurniture.byId(id.asCraftKey())
        requireNotNull(furniture) { "CraftEngine item is not a furniture: $id" }
        CraftEngineFurniture.place(loc, id.asCraftKey(), furniture.anyVariantName(), playSound)
    }

    override fun isPrimaryThreadEnforced(): Boolean {
        return true
    }

}