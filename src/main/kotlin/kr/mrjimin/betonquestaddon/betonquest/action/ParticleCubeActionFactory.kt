package kr.mrjimin.betonquestaddon.betonquest.action

import org.betonquest.betonquest.api.instruction.Instruction
import org.betonquest.betonquest.api.quest.action.PlayerAction
import org.betonquest.betonquest.api.quest.action.PlayerActionFactory
import org.bukkit.Particle

class ParticleCubeActionFactory : PlayerActionFactory {
    override fun parsePlayer(instruction: Instruction): PlayerAction {
        val particle = instruction.enumeration(Particle::class.java).get()
        val location = instruction.location().get()
        val width = instruction.number().get("width", 1.0)
        val height = instruction.number().get("height", 1.0)
        val interval = instruction.number().get("interval", 0.125)
        return ParticleCubeAction(particle, location, width, height, interval)
    }
}