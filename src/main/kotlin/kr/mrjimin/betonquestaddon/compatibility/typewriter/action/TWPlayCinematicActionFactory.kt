package kr.mrjimin.betonquestaddon.compatibility.typewriter.action

import org.betonquest.betonquest.api.instruction.Instruction
import org.betonquest.betonquest.api.quest.action.PlayerAction
import org.betonquest.betonquest.api.quest.action.PlayerActionFactory

class TWPlayCinematicActionFactory : PlayerActionFactory {
    override fun parsePlayer(instruction: Instruction): PlayerAction {
        val cinematic = instruction.string().get()
        return  TWPlayCinematicAction(cinematic)
    }
}