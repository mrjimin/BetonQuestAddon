package kr.mrjimin.betonquestaddon.compatibility.nexo.action

import com.nexomc.nexo.api.NexoBlocks
import com.nexomc.nexo.api.NexoFurniture
import org.betonquest.betonquest.api.QuestException
import org.betonquest.betonquest.api.instruction.Argument
import org.betonquest.betonquest.api.profile.Profile
import org.betonquest.betonquest.api.quest.action.PlayerAction
import org.bukkit.Location
import org.bukkit.Rotation
import org.bukkit.block.BlockFace

class NexoSetFurnitureAction(
    private val itemId: Argument<String>,
    private val location: Argument<Location>,
    private val rotation: Argument<Rotation>,
    private val blockFace: Argument<BlockFace>
) : PlayerAction {

    override fun execute(profile: Profile) {
        val id = itemId.getValue(profile)

        id.takeIf { NexoBlocks.isCustomBlock(id) }
            ?: throw QuestException("Nexo item is not a furniture: $id")

        NexoFurniture.place(id, location.getValue(profile), rotation.getValue(profile), blockFace.getValue(profile))
    }

    override fun isPrimaryThreadEnforced(): Boolean = true

}