package kr.mrjimin.betonquestaddon.compatibility.itemsadder.objective

import dev.lone.itemsadder.api.CustomFurniture
import dev.lone.itemsadder.api.Events.FurnitureBreakEvent
import dev.lone.itemsadder.api.Events.FurnitureInteractEvent
import dev.lone.itemsadder.api.Events.FurniturePlacedEvent
import kr.mrjimin.betonquestaddon.config.NotifyMessage
import kr.mrjimin.betonquestaddon.util.DefaultOptions
import org.betonquest.betonquest.api.CountingObjective
import org.betonquest.betonquest.api.instruction.Argument
import org.betonquest.betonquest.api.profile.OnlineProfile
import org.betonquest.betonquest.api.quest.objective.service.ObjectiveService
import org.bukkit.event.Cancellable

class ItemsAdderFurnitureObjective(
    service: ObjectiveService,
    private val options: DefaultOptions,
    notifyMessage: NotifyMessage,
    private val id: Argument<List<String>>
) : CountingObjective(service, options.amount, notifyMessage.toKey()) {

    fun onPlace(event: FurniturePlacedEvent, profile: OnlineProfile) {
        handle(profile, event.furniture, event)
    }

    fun onBreak(event: FurnitureBreakEvent, profile: OnlineProfile) {
        handle(profile, event.furniture, event)
    }

    fun onInteract(event: FurnitureInteractEvent, profile: OnlineProfile) {
        handle(profile, event.furniture, event)
    }

    fun handle(profile: OnlineProfile, target: CustomFurniture?, event: Cancellable) {
        val targetId = target?.namespacedID

        options.locationFilter?.let { filter ->
            if (!filter.matches(profile, target?.entity?.location!!)) return
        }

        if (options.isCancelled.getValue(profile)) {
            event.isCancelled = true
            return
        }

        if (id.getValue(profile).equals(targetId)) {
            getCountingData(profile)?.progress()
            completeIfDoneOrNotify(profile)
        }
    }


}