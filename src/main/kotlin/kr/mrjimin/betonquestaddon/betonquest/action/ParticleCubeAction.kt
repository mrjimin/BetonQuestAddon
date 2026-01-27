package kr.mrjimin.betonquestaddon.betonquest.action

import kr.mrjimin.betonquestaddon.util.drawCube
import org.betonquest.betonquest.api.instruction.Argument
import org.betonquest.betonquest.api.profile.Profile
import org.betonquest.betonquest.api.quest.action.PlayerAction
import org.bukkit.Location
import org.bukkit.Particle
import org.bukkit.entity.Player

class ParticleCubeAction(
    private val particleId: Argument<Particle>,
    private val location: Argument<Location>,
    private val width: Argument<Number>,
    private val height: Argument<Number>,
    private val interval: Argument<Number>
) : PlayerAction {
    override fun execute(profile: Profile) {
        val particle = particleId.getValue(profile)
        val location = location.getValue(profile)
        val width = width.getValue(profile).toDouble()
        val height = height.getValue(profile).toDouble()
        val interval = interval.getValue(profile).toDouble()

        val player = profile.player as Player
        player.drawCube(particle, location, width, height, interval)
    }
}