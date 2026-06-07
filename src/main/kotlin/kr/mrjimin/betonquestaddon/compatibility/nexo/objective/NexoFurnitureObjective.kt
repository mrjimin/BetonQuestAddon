package kr.mrjimin.betonquestaddon.compatibility.nexo.objective

import com.nexomc.nexo.api.NexoFurniture
import com.nexomc.nexo.api.events.furniture.NexoFurnitureBreakEvent
import com.nexomc.nexo.api.events.furniture.NexoFurnitureInteractEvent
import com.nexomc.nexo.api.events.furniture.NexoFurniturePlaceEvent
import kr.mrjimin.betonquestaddon.config.NotifyMessage
import kr.mrjimin.betonquestaddon.util.DefaultOptions
import org.betonquest.betonquest.api.CountingObjective
import org.betonquest.betonquest.api.instruction.Argument
import org.betonquest.betonquest.api.profile.OnlineProfile
import org.betonquest.betonquest.api.quest.objective.service.ObjectiveService
import org.bukkit.entity.Entity
import org.bukkit.event.Cancellable

class NexoFurnitureObjective(
    service: ObjectiveService,
    private val options: DefaultOptions,
    notifyMessage: NotifyMessage,
    private val id: Argument<List<String>>
) : CountingObjective(service, options.amount, notifyMessage.toKey()) {

    fun onPlace(event: NexoFurniturePlaceEvent, profile: OnlineProfile) {
        handle(profile, event.baseEntity, event)
    }

    fun onBreak(event: NexoFurnitureBreakEvent, profile: OnlineProfile) {
        handle(profile, event.baseEntity, event)
    }

    fun onInteract(event: NexoFurnitureInteractEvent, profile: OnlineProfile) {
        handle(profile, event.baseEntity, event)
    }

    fun handle(profile: OnlineProfile, target: Entity, event: Cancellable) {
        val targetId = NexoFurniture.furnitureMechanic(target)?.itemID

        options.locationFilter?.let { filter ->
            if (!filter.matches(profile, target.location)) return
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