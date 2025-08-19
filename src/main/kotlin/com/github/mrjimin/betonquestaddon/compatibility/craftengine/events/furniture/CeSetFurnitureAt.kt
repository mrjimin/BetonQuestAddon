package com.github.mrjimin.betonquestaddon.compatibility.craftengine.events.furniture

import com.github.mrjimin.betonquestaddon.hook.CraftEngineHook
import com.github.mrjimin.betonquestaddon.hook.CraftEngineHook.toKeyOrNull
import net.momirealms.craftengine.bukkit.api.CraftEngineFurniture
import org.betonquest.betonquest.api.instruction.variable.Variable
import org.betonquest.betonquest.api.profile.Profile
import org.betonquest.betonquest.api.quest.QuestException
import org.betonquest.betonquest.api.quest.event.PlayerEvent
import org.bukkit.Location

class CeSetFurnitureAt(
    private val itemID: Variable<String>,
    private val loc: Variable<Location>,
    private val playSound: Variable<Boolean>
) : PlayerEvent {

    override fun execute(profile: Profile) {
        val loc = loc.getValue(profile)
        val id = itemID.getValue(profile)
        val playSound = playSound.getValue(profile)

        val key = id.toKeyOrNull() ?: return
        val furniture = CraftEngineFurniture.byId(key) ?: return

        if (!CraftEngineHook.isFurniture(key))
            throw QuestException("CraftEngine item is not a furniture: $id")

        CraftEngineFurniture.place(loc, key, furniture.anyAnchorType, playSound)
    }
}