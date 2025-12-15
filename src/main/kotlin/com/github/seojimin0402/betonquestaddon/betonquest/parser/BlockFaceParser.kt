package com.github.seojimin0402.betonquestaddon.betonquest.parser

import org.betonquest.betonquest.api.quest.QuestException
import org.betonquest.betonquest.api.instruction.argument.Argument
import org.bukkit.block.BlockFace

object BlockFaceParser : Argument<BlockFace> {
    override fun apply(value: String): BlockFace {
        return BlockFace.entries.find { it.name.equals(value, ignoreCase = true) }
            ?: throw QuestException("Invalid BlockFace value: '$value'. Valid values are: ${BlockFace.entries.joinToString(", ") { it.name }}")
    }
}
