package com.github.mrjimin.betonquestaddon.compatibilityold.craftengine.events.block

import com.github.mrjimin.betonquestaddon.hook.CraftEngineHook
import com.github.mrjimin.betonquestaddon.hook.CraftEngineHook.toKeyOrNull
import net.momirealms.craftengine.bukkit.api.CraftEngineBlocks
import net.momirealms.craftengine.libraries.nbt.CompoundTag
import org.betonquest.betonquest.api.instruction.variable.Variable
import org.betonquest.betonquest.api.profile.Profile
import org.betonquest.betonquest.api.quest.QuestException
import org.betonquest.betonquest.api.quest.event.PlayerEvent
import org.bukkit.Location

class CeSetBlockAt(
    private val itemID: Variable<String>,
    private val loc: Variable<Location>,
    private val playSound: Variable<Boolean>
) : PlayerEvent {

    override fun execute(profile: Profile) {
        val loc = loc.getValue(profile)
        val id = itemID.getValue(profile)
        val playSound = playSound.getValue(profile)

        val key = id.toKeyOrNull() ?: return

        if (!CraftEngineHook.isCustomBlock(key))
            throw QuestException("CraftEngine item is not a block: $id")

        CraftEngineBlocks.place(loc, key, CompoundTag(), playSound)
    }
}