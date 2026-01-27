package kr.mrjimin.betonquestaddon.compatibility.worldguard.condition

import kr.mrjimin.betonquestaddon.compatibility.worldguard.TargetType
import org.betonquest.betonquest.api.instruction.Argument
import org.betonquest.betonquest.api.instruction.Instruction
import org.betonquest.betonquest.api.quest.condition.PlayerCondition
import org.betonquest.betonquest.api.quest.condition.PlayerConditionFactory
import org.betonquest.betonquest.api.quest.condition.PlayerlessCondition
import org.betonquest.betonquest.api.quest.condition.PlayerlessConditionFactory
import org.betonquest.betonquest.api.quest.condition.nullable.NullableConditionAdapter
import org.betonquest.betonquest.quest.condition.ThrowExceptionPlayerlessCondition
import org.bukkit.World

class WGHasConditionFactory(
    private val targetType: TargetType,
) : PlayerConditionFactory, PlayerlessConditionFactory {
    override fun parsePlayer(instruction: Instruction): PlayerCondition {
        return NullableConditionAdapter(
            WGHasCondition(
                targetType,
                instruction.getWorld(),
                instruction.string().get("region").orElse(null)
            )
        )
    }

    override fun parsePlayerless(instruction: Instruction): PlayerlessCondition {
        val world = instruction.world().get("world").orElse(null)
            ?: return ThrowExceptionPlayerlessCondition()

        val region = instruction.string().get("region").orElse(null)
            ?: return ThrowExceptionPlayerlessCondition()

        return NullableConditionAdapter(
            WGHasCondition(
                targetType,
                world,
                region
            )
        )
    }

    private fun Instruction.getWorld(): Argument<World> {
        val world = string().get("world", "%location.world%").getValue(null)
        return chainForArgument(world).world().get()
    }
}

