package kr.mrjimin.betonquestaddon.compatibility.customnameplates.action

import net.momirealms.customnameplates.api.CustomNameplatesAPI
import org.betonquest.betonquest.api.QuestException
import org.betonquest.betonquest.api.instruction.Argument
import org.betonquest.betonquest.api.profile.Profile
import org.betonquest.betonquest.api.quest.action.PlayerAction

class NpApplyAction(
    private val nameplate: Argument<String>
) : PlayerAction {

    override fun execute(profile: Profile) {
        val id = nameplate.getValue(profile)

        id.takeIf { CustomNameplatesAPI.getInstance().getNameplate(it).isPresent }
            ?: throw QuestException("Registered nameplate not found for ID: $id")

        CustomNameplatesAPI.getInstance().getPlayer(profile.playerUUID)!!.setNameplateData(id)
    }

}