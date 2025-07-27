package com.github.mrjimin.betonquestaddon.compatibility.nexo.events.furniture

import com.github.mrjimin.betonquestaddon.betonquest.parser.BlockFaceParser
import com.github.mrjimin.betonquestaddon.betonquest.parser.RotationParser
import com.github.mrjimin.betonquestaddon.compatibility.nexo.NxParser
import org.betonquest.betonquest.api.quest.event.PlayerEvent
import org.betonquest.betonquest.api.quest.event.PlayerEventFactory
import org.betonquest.betonquest.instruction.Instruction
import org.betonquest.betonquest.instruction.argument.Argument
import org.betonquest.betonquest.instruction.variable.Variable
import org.betonquest.betonquest.quest.PrimaryServerThreadData
import org.betonquest.betonquest.quest.event.PrimaryServerThreadEvent
import org.bukkit.Location
import org.bukkit.Rotation
import org.bukkit.block.BlockFace

class NxSetFurnitureAtEventFactor(
    private val data: PrimaryServerThreadData
) : PlayerEventFactory {

    override fun parsePlayer(instruction: Instruction): PlayerEvent {
        val itemID: Variable<String> = instruction.get(NxParser)
        val location: Variable<Location> = instruction.get(Argument.LOCATION)
        val rotation: Variable<Rotation> = instruction.get(RotationParser)
        val blockFace: Variable<BlockFace> = instruction.get(BlockFaceParser)
        return PrimaryServerThreadEvent(NxSetFurnitureAt(itemID, location, rotation, blockFace), data)
    }
}