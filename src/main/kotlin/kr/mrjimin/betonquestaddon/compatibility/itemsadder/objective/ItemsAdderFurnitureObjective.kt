package kr.mrjimin.betonquestaddon.compatibility.itemsadder.objective

import dev.lone.itemsadder.api.CustomFurniture
import dev.lone.itemsadder.api.Events.FurnitureBreakEvent
import dev.lone.itemsadder.api.Events.FurnitureInteractEvent
import dev.lone.itemsadder.api.Events.FurniturePlacedEvent
import kr.mrjimin.betonquestaddon.betonquest.objective.AddonObjective
import kr.mrjimin.betonquestaddon.config.NotifyMessage
import kr.mrjimin.betonquestaddon.util.ObjectiveOptions
import org.betonquest.betonquest.api.instruction.Argument
import org.betonquest.betonquest.api.profile.OnlineProfile
import org.betonquest.betonquest.api.quest.objective.service.ObjectiveService
import org.bukkit.Location

class ItemsAdderFurnitureObjective(
    service: ObjectiveService,
    amount: Argument<Number>,
    options: ObjectiveOptions,
    notifyMessage: NotifyMessage
) : AddonObjective<CustomFurniture?>(service, amount, options, notifyMessage) {

    fun onPlace(event: FurniturePlacedEvent, profile: OnlineProfile) {
        handle(profile, event.furniture, event)
    }

    fun onBreak(event: FurnitureBreakEvent, profile: OnlineProfile) {
        handle(profile, event.furniture, event)
    }

    fun onInteract(event: FurnitureInteractEvent, profile: OnlineProfile) {
        handle(profile, event.furniture, event)
    }

    override fun getId(target: CustomFurniture?): String? {
        return target?.namespacedID
    }

    override fun getLocation(target: CustomFurniture?): Location {
        return target?.entity?.location!!
    }


}