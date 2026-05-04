package kr.mrjimin.betonquestaddon.compatibility.craftengine.objective

import kr.mrjimin.betonquestaddon.betonquest.objective.AddonObjective
import kr.mrjimin.betonquestaddon.config.NotifyMessage
import kr.mrjimin.betonquestaddon.util.ObjectiveOptions
import net.momirealms.craftengine.bukkit.api.event.FurnitureBreakEvent
import net.momirealms.craftengine.bukkit.api.event.FurnitureInteractEvent
import net.momirealms.craftengine.bukkit.api.event.FurniturePlaceEvent
import net.momirealms.craftengine.bukkit.entity.furniture.BukkitFurniture
import org.betonquest.betonquest.api.instruction.Argument
import org.betonquest.betonquest.api.profile.OnlineProfile
import org.betonquest.betonquest.api.quest.objective.service.ObjectiveService
import org.bukkit.Location

class CraftEngineFurnitureObjective(
    service: ObjectiveService,
    amount: Argument<Number>,
    options: ObjectiveOptions,
    notifyMessage: NotifyMessage
) : AddonObjective<BukkitFurniture>(service, amount, options, notifyMessage) {

    fun onPlace(event: FurniturePlaceEvent, profile: OnlineProfile) {
        handle(profile, event.furniture(), event)
    }

    fun onBreak(event: FurnitureBreakEvent, profile: OnlineProfile) {
        handle(profile, event.furniture(), event)
    }

    fun onInteract(event: FurnitureInteractEvent, profile: OnlineProfile) {
        handle(profile, event.furniture(), event)
    }

    override fun getId(target: BukkitFurniture): String {
        return target.id().toString()
    }

    override fun getLocation(target: BukkitFurniture): Location {
        return target.location()
    }
}