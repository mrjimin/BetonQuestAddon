package kr.mrjimin.betonquestaddon.compatibility.nexo.action

import com.nexomc.nexo.api.NexoBlocks
import org.betonquest.betonquest.api.QuestException
import org.betonquest.betonquest.api.instruction.Argument
import org.betonquest.betonquest.api.profile.Profile
import org.betonquest.betonquest.api.quest.action.PlayerAction
import org.bukkit.Location

class NexoSetBlockAction(
    private val itemId: Argument<String>,
    private val location: Argument<Location>
) : PlayerAction {

    override fun execute(profile: Profile) {
        val id = itemId.getValue(profile)

        id.takeIf { NexoBlocks.isCustomBlock(id) }
            ?: throw QuestException("Nexo item is not a block: $id")

        NexoBlocks.place(id, location.getValue(profile))
    }

    override fun isPrimaryThreadEnforced(): Boolean = true
}