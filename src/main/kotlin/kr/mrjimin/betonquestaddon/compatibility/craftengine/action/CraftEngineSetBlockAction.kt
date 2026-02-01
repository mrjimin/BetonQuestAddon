package kr.mrjimin.betonquestaddon.compatibility.craftengine.action

import net.momirealms.craftengine.bukkit.api.CraftEngineBlocks
import net.momirealms.craftengine.core.util.Key
import org.betonquest.betonquest.api.QuestException
import org.betonquest.betonquest.api.instruction.Argument
import org.betonquest.betonquest.api.profile.Profile
import org.betonquest.betonquest.api.quest.action.PlayerAction
import org.bukkit.Location

class CraftEngineSetBlockAction(
    private val itemId: Argument<String>,
    private val location: Argument<Location>,
    private val playSound: Argument<Boolean>,
) : PlayerAction {

    override fun execute(profile: Profile) {
        val id = itemId.getValue(profile)

        id.takeIf { CraftEngineBlocks.byId(Key.of(id)) != null }
            ?: throw QuestException("CraftEngine item is not a block: $id")

        CraftEngineBlocks.place(location.getValue(profile), Key.of(id), playSound.getValue(profile))
    }

    override fun isPrimaryThreadEnforced(): Boolean = true
}