package kr.mrjimin.betonquestaddon.compatibility.craftengine.objective

import kr.mrjimin.betonquestaddon.betonquest.objective.AddonObjective
import kr.mrjimin.betonquestaddon.config.NotifyMessage
import kr.mrjimin.betonquestaddon.util.ObjectiveOptions
import net.momirealms.craftengine.bukkit.api.BukkitAdaptor
import net.momirealms.craftengine.bukkit.api.event.CustomBlockBreakEvent
import net.momirealms.craftengine.bukkit.api.event.CustomBlockInteractEvent
import net.momirealms.craftengine.bukkit.api.event.CustomBlockPlaceEvent
import org.betonquest.betonquest.api.instruction.Argument
import org.betonquest.betonquest.api.profile.OnlineProfile
import org.betonquest.betonquest.api.quest.objective.service.ObjectiveService
import org.bukkit.Location
import org.bukkit.block.Block

class CraftEngineBlockObjective(
    service: ObjectiveService,
    amount: Argument<Number>,
    options: ObjectiveOptions,
    notifyMessage: NotifyMessage
) : AddonObjective<Block>(service, amount, options, notifyMessage) {

    fun onPlace(event: CustomBlockPlaceEvent, profile: OnlineProfile) {
        handle(profile, event.bukkitBlock(), event)
    }

    fun onBreak(event: CustomBlockBreakEvent, profile: OnlineProfile) {
        handle(profile, event.bukkitBlock(), event)
    }

    fun onInteract(event: CustomBlockInteractEvent, profile: OnlineProfile) {
        handle(profile, event.bukkitBlock(), event)
    }

    override fun getId(target: Block): String {
        return BukkitAdaptor.adapt(target).id().toString()
    }

    override fun getLocation(target: Block): Location {
        return target.location
    }
}