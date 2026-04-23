package kr.mrjimin.betonquestaddon.betonquest.condition

import org.betonquest.betonquest.api.instruction.Instruction
import org.betonquest.betonquest.api.quest.condition.*
import org.bukkit.Location

class LocationConditionFactory(
    private val mechanicIdProvider: (Location) -> String?
) : PlayerConditionFactory, PlayerlessConditionFactory {

    override fun parsePlayer(instruction: Instruction): PlayerCondition =
        NullableConditionAdapter(parseInstruction(instruction))

    override fun parsePlayerless(instruction: Instruction): PlayerlessCondition =
        NullableConditionAdapter(parseInstruction(instruction))

    private fun parseInstruction(instruction: Instruction): LocationCondition {
        val itemId = instruction.string().get()
        val location = instruction.location().get()
        return LocationCondition(itemId, location, mechanicIdProvider)
    }

}
