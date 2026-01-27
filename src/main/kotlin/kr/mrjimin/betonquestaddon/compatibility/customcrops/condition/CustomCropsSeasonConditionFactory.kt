package kr.mrjimin.betonquestaddon.compatibility.customcrops.condition

import net.momirealms.customcrops.api.core.world.Season
import org.betonquest.betonquest.api.instruction.Argument
import org.betonquest.betonquest.api.instruction.Instruction
import org.betonquest.betonquest.api.quest.condition.PlayerCondition
import org.betonquest.betonquest.api.quest.condition.PlayerConditionFactory
import org.betonquest.betonquest.api.quest.condition.PlayerlessCondition
import org.betonquest.betonquest.api.quest.condition.PlayerlessConditionFactory
import org.betonquest.betonquest.api.quest.condition.nullable.NullableConditionAdapter
import org.betonquest.betonquest.quest.condition.ThrowExceptionPlayerlessCondition
import org.bukkit.World

class CustomCropsSeasonConditionFactory : PlayerConditionFactory, PlayerlessConditionFactory {
    override fun parsePlayer(instruction: Instruction): PlayerCondition {
        return NullableConditionAdapter(CustomCropsSeasonCondition(
            instruction.getSeason(),
            instruction.getWorld()
        ))
    }

    override fun parsePlayerless(instruction: Instruction): PlayerlessCondition {
        val world = instruction.world().get("world").orElse(null)
            ?: return ThrowExceptionPlayerlessCondition()
        return NullableConditionAdapter(CustomCropsSeasonCondition(instruction.getSeason(), world))
    }

    private fun Instruction.getSeason(): Argument<Season> =
        enumeration(Season::class.java).get()

    private fun Instruction.getWorld(): Argument<World> {
        val world = string().get("world", "%location.world%").getValue(null)
        return chainForArgument(world).world().get()
    }

}