package kr.mrjimin.betonquestaddon.compatibility.craftengine.objective

import kr.mrjimin.betonquestaddon.config.NotifyMessage
import kr.mrjimin.betonquestaddon.util.DefaultOptions
import net.momirealms.craftengine.bukkit.api.BukkitAdaptor
import net.momirealms.craftengine.bukkit.api.event.CustomBlockBreakEvent
import net.momirealms.craftengine.bukkit.api.event.CustomBlockInteractEvent
import net.momirealms.craftengine.bukkit.api.event.CustomBlockPlaceEvent
import org.betonquest.betonquest.api.CountingObjective
import org.betonquest.betonquest.api.instruction.Argument
import org.betonquest.betonquest.api.profile.OnlineProfile
import org.betonquest.betonquest.api.quest.objective.service.ObjectiveService
import org.bukkit.block.Block
import org.bukkit.event.Cancellable

class CraftEngineBlockObjective(
    service: ObjectiveService,
    private val options: DefaultOptions,
    notifyMessage: NotifyMessage,
    private val id: Argument<List<String>>
) : CountingObjective(service, options.amount, notifyMessage.toKey()) {

    fun onPlace(event: CustomBlockPlaceEvent, profile: OnlineProfile) {
        handle(profile, event.bukkitBlock(), event)
    }

    fun onBreak(event: CustomBlockBreakEvent, profile: OnlineProfile) {
        handle(profile, event.bukkitBlock(), event)
    }

    fun onInteract(event: CustomBlockInteractEvent, profile: OnlineProfile) {
        handle(profile, event.bukkitBlock(), event)
    }

    fun handle(profile: OnlineProfile, target: Block, event: Cancellable) {
        val targetId = BukkitAdaptor.adapt(target).id().toString()

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