package kr.mrjimin.betonquestaddon.compatibility.nexo.objective

import com.nexomc.nexo.api.NexoFurniture
import com.nexomc.nexo.api.events.furniture.NexoFurnitureBreakEvent
import com.nexomc.nexo.api.events.furniture.NexoFurnitureInteractEvent
import com.nexomc.nexo.api.events.furniture.NexoFurniturePlaceEvent
import kr.mrjimin.betonquestaddon.betonquest.objective.AddonObjective
import kr.mrjimin.betonquestaddon.config.NotifyMessage
import kr.mrjimin.betonquestaddon.util.DefaultOptions
import org.betonquest.betonquest.api.instruction.Argument
import org.betonquest.betonquest.api.profile.OnlineProfile
import org.betonquest.betonquest.api.quest.objective.service.ObjectiveService
import org.bukkit.entity.Entity
import org.bukkit.event.Cancellable

class NexoFurnitureObjective(
    service: ObjectiveService,
    options: DefaultOptions,
    notifyMessage: NotifyMessage,
    private val id: Argument<List<String>>
) : AddonObjective(service, options, notifyMessage) {

    fun onPlace(event: NexoFurniturePlaceEvent, profile: OnlineProfile) {
        handle(profile, event.baseEntity, event)
    }

    fun onBreak(event: NexoFurnitureBreakEvent, profile: OnlineProfile) {
        handle(profile, event.baseEntity, event)
    }

    fun onInteract(event: NexoFurnitureInteractEvent, profile: OnlineProfile) {
        handle(profile, event.baseEntity, event)
    }

    private fun handle(profile: OnlineProfile, target: Entity, event: Cancellable) {
        val targetId = NexoFurniture.furnitureMechanic(target)?.itemID
        success(profile, id.getValue(profile).equals(targetId), event)
    }

}