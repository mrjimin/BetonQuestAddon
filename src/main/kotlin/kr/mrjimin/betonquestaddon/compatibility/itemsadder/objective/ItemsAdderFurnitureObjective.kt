package kr.mrjimin.betonquestaddon.compatibility.itemsadder.objective

import dev.lone.itemsadder.api.CustomFurniture
import dev.lone.itemsadder.api.Events.FurnitureBreakEvent
import dev.lone.itemsadder.api.Events.FurnitureInteractEvent
import dev.lone.itemsadder.api.Events.FurniturePlacedEvent
import kr.mrjimin.betonquestaddon.betonquest.objective.AddonObjective
import kr.mrjimin.betonquestaddon.config.NotifyMessage
import kr.mrjimin.betonquestaddon.util.DefaultOptions
import org.betonquest.betonquest.api.instruction.Argument
import org.betonquest.betonquest.api.profile.OnlineProfile
import org.betonquest.betonquest.api.quest.objective.service.ObjectiveService
import org.bukkit.event.Cancellable

class ItemsAdderFurnitureObjective(
    service: ObjectiveService,
    options: DefaultOptions,
    notifyMessage: NotifyMessage,
    private val id: Argument<List<String>>
) : AddonObjective(service, options, notifyMessage) {

    fun onPlace(event: FurniturePlacedEvent, profile: OnlineProfile) {
        handle(profile, event.furniture, event)
    }

    fun onBreak(event: FurnitureBreakEvent, profile: OnlineProfile) {
        handle(profile, event.furniture, event)
    }

    fun onInteract(event: FurnitureInteractEvent, profile: OnlineProfile) {
        handle(profile, event.furniture, event)
    }

    private fun handle(profile: OnlineProfile, target: CustomFurniture?, event: Cancellable) {
        val targetId = target?.namespacedID
        success(profile, id.getValue(profile).equals(targetId), event)
    }

}