package kr.mrjimin.betonquestaddon.util

import kr.mrjimin.betonquestaddon.betonquest.objective.LocationFilter
import org.betonquest.betonquest.api.instruction.Argument
import org.betonquest.betonquest.api.instruction.Instruction

fun Instruction.parseDefaultOptions(): DefaultOptions {
    val amount = number().get("amount", 1)
    val isCancelled = bool().get("isCancelled", false)
    val locationFilter = location().get("location").orElse(null)?.let { location ->
        val range = number().get("range", 0)
        LocationFilter(location, range)
    }
    return DefaultOptions(amount, isCancelled, locationFilter)
}

data class DefaultOptions(
    val amount: Argument<Number>,
    val isCancelled: Argument<Boolean>,
    val locationFilter: LocationFilter? = null
)