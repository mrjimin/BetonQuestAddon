package com.github.mrjimin.betonquestaddon.compatibility.craftengine.objective

import com.github.mrjimin.betonquestaddon.config.NotifyMessage
import net.momirealms.craftengine.bukkit.api.BukkitAdaptors
import net.momirealms.craftengine.bukkit.api.event.CustomBlockBreakEvent
import net.momirealms.craftengine.bukkit.api.event.CustomBlockInteractEvent
import net.momirealms.craftengine.bukkit.api.event.CustomBlockPlaceEvent
import org.betonquest.betonquest.api.CountingObjective
import org.betonquest.betonquest.api.QuestException
import org.betonquest.betonquest.api.instruction.Argument
import org.betonquest.betonquest.api.quest.objective.service.ObjectiveService
import org.bukkit.block.Block
import org.bukkit.entity.Player
import org.bukkit.event.Cancellable

class CraftEngineBlockObjective(
    service: ObjectiveService,
    targetAmount: Argument<Number>,
    private val identifier: Argument<List<String>>,
    private val isCancelled: Argument<Boolean>,
    notifyMessage: NotifyMessage
) : CountingObjective(service, targetAmount, notifyMessage.toKey()) {

    fun onPlace(event: CustomBlockPlaceEvent) {
        handle(event.player, event.bukkitBlock(), event)
    }

    fun onBreak(event: CustomBlockBreakEvent) {
        handle(event.player, event.bukkitBlock(), event)
    }

    fun onInteract(event: CustomBlockInteractEvent) {
        handle(event.player, event.bukkitBlock(), event)
    }

    @Throws(QuestException::class)
    private fun handle(player: Player, block: Block, event: Cancellable) {
        val profile = service.profileProvider.getProfile(player) ?: return
        if (!service.containsProfile(profile) || !service.checkConditions(profile)) return

        if (identifier.getValue(profile).contains(BukkitAdaptors.adapt(block).id().toString())) {
            getCountingData(profile)?.progress()
            completeIfDoneOrNotify(profile)

            if (isCancelled.getValue(profile)) event.isCancelled = true
        }
    }
}