package kr.mrjimin.betonquestaddon.compatibility.typewriter.action

import org.betonquest.betonquest.api.QuestException
import org.betonquest.betonquest.api.instruction.Argument
import org.betonquest.betonquest.api.profile.Profile
import org.betonquest.betonquest.api.quest.action.PlayerAction
import org.bukkit.Bukkit

class TWPlayCinematicAction(
    private val cinematic: Argument<String>
) : PlayerAction {
    override fun execute(profile: Profile) {
        val id = cinematic.getValue(profile)
        val playerName = profile.player.name

        try {
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "typewriter:typewriter cinematic start $id $playerName")
        } catch (e: Exception) {
            throw QuestException("Invalid TypeWriter Cinematic: '$id'", e)
        }
    }
}