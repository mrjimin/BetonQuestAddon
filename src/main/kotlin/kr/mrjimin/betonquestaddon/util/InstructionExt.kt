package kr.mrjimin.betonquestaddon.util

import kr.mrjimin.betonquestaddon.betonquest.objective.LocationFilter
import org.betonquest.betonquest.api.instruction.Argument
import org.betonquest.betonquest.api.instruction.Instruction
import org.betonquest.betonquest.api.instruction.type.ItemWrapper

fun Instruction.parseOptions(): Pair<Argument<Number>, ObjectiveOptions> {
    val amount = number().get("amount", 1)

    val ids = string().list().get("id").orElse(null)
    val targetIds = string().list().get("target").orElse(null)
    val item = item().get("item").orElse(null)
    val isCancelled = bool().get("isCancelled", false)

    val location = location().get("location").orElse(null)
    val range = number().get("range", 0)
    val locationFilter = location?.let { LocationFilter(it, range) }

    return amount to ObjectiveOptions(
        ids = ids,
        targetIds = targetIds,
        item = item,
        isCancelled = isCancelled,
        locationFilter = locationFilter
    )
}

data class ObjectiveOptions(
    val ids: Argument<List<String>>? = null,
    val targetIds: Argument<List<String>>? = null,
    val item: Argument<ItemWrapper>? = null,
    val isCancelled: Argument<Boolean>? = null,
    val locationFilter: LocationFilter? = null
)