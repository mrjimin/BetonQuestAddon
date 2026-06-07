package kr.mrjimin.betonquestaddon.compatibility.craftengine.objective

import kr.mrjimin.betonquestaddon.config.NotifyMessage
import kr.mrjimin.betonquestaddon.util.DefaultOptions
import net.momirealms.craftengine.bukkit.api.event.FurnitureBreakEvent
import net.momirealms.craftengine.bukkit.api.event.FurnitureInteractEvent
import net.momirealms.craftengine.bukkit.api.event.FurniturePlaceEvent
import net.momirealms.craftengine.bukkit.entity.furniture.BukkitFurniture
import org.betonquest.betonquest.api.CountingObjective
import org.betonquest.betonquest.api.instruction.Argument
import org.betonquest.betonquest.api.profile.OnlineProfile
import org.betonquest.betonquest.api.quest.objective.service.ObjectiveService
import org.bukkit.event.Cancellable

class CraftEngineFurnitureObjective(
    service: ObjectiveService,
    private val options: DefaultOptions,
    notifyMessage: NotifyMessage,
    private val id: Argument<List<String>>
) : CountingObjective(service, options.amount, notifyMessage.toKey()) {

    fun onPlace(event: FurniturePlaceEvent, profile: OnlineProfile) {
        handle(profile, event.furniture(), event)
    }

    fun onBreak(event: FurnitureBreakEvent, profile: OnlineProfile) {
        handle(profile, event.furniture(), event)
    }

    fun onInteract(event: FurnitureInteractEvent, profile: OnlineProfile) {
        handle(profile, event.furniture(), event)
    }

    fun handle(profile: OnlineProfile, target: BukkitFurniture, event: Cancellable) {
        val targetId = target.id().toString()

        options.locationFilter?.let { filter ->
            if (!filter.matches(profile, target.location())) return
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