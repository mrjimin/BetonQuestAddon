package com.github.mrjimin.betonquestaddon.compatibility.nexo.events.furniture

import com.github.mrjimin.betonquestaddon.betonquest.parser.BlockFaceParser
import com.github.mrjimin.betonquestaddon.betonquest.parser.RotationParser
import com.github.mrjimin.betonquestaddon.compatibility.nexo.NxParser
import org.betonquest.betonquest.api.instruction.Instruction
import org.betonquest.betonquest.api.instruction.argument.Argument
import org.betonquest.betonquest.api.quest.event.PlayerEvent
import org.betonquest.betonquest.api.quest.event.PlayerEventFactory
import org.betonquest.betonquest.quest.PrimaryServerThreadData
import org.betonquest.betonquest.quest.event.PrimaryServerThreadEvent

class NxSetFurnitureAtEventFactor(
    private val data: PrimaryServerThreadData
) : PlayerEventFactory {

    override fun parsePlayer(instruction: Instruction): PlayerEvent {
        val itemID = instruction.get(NxParser)
        val location = instruction.get(Argument.LOCATION)
        val rotation = instruction.get(RotationParser)
        val blockFace = instruction.get(BlockFaceParser)
        return PrimaryServerThreadEvent(NxSetFurnitureAt(itemID, location, rotation, blockFace), data)
    }
}