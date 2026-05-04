package kr.mrjimin.betonquestaddon.compatibility.nexo.objective

import com.nexomc.nexo.api.NexoFurniture
import com.nexomc.nexo.api.events.furniture.NexoFurnitureBreakEvent
import com.nexomc.nexo.api.events.furniture.NexoFurnitureInteractEvent
import com.nexomc.nexo.api.events.furniture.NexoFurniturePlaceEvent
import kr.mrjimin.betonquestaddon.betonquest.objective.AddonObjective
import kr.mrjimin.betonquestaddon.config.NotifyMessage
import kr.mrjimin.betonquestaddon.util.ObjectiveOptions
import org.betonquest.betonquest.api.instruction.Argument
import org.betonquest.betonquest.api.profile.OnlineProfile
import org.betonquest.betonquest.api.quest.objective.service.ObjectiveService
import org.bukkit.Location
import org.bukkit.entity.Entity

class NexoFurnitureObjective(
    service: ObjectiveService,
    amount: Argument<Number>,
    options: ObjectiveOptions,
    notifyMessage: NotifyMessage
) : AddonObjective<Entity>(service, amount, options, notifyMessage) {

    fun onPlace(event: NexoFurniturePlaceEvent, profile: OnlineProfile) {
        handle(profile, event.baseEntity, event)
    }

    fun onBreak(event: NexoFurnitureBreakEvent, profile: OnlineProfile) {
        handle(profile, event.baseEntity, event)
    }

    fun onInteract(event: NexoFurnitureInteractEvent, profile: OnlineProfile) {
        handle(profile, event.baseEntity, event)
    }

    override fun getId(target: Entity): String? {
        return NexoFurniture.furnitureMechanic(target)?.itemID
    }

    override fun getLocation(target: Entity): Location {
        return target.location
    }

}