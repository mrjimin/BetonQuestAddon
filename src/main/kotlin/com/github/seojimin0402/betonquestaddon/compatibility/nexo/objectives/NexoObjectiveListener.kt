package com.github.seojimin0402.betonquestaddon.compatibility.nexo.objectives

import com.github.seojimin0402.betonquestaddon.util.event.ActionType
import com.github.seojimin0402.betonquestaddon.util.event.TargetType
import com.nexomc.nexo.api.NexoFurniture
import com.nexomc.nexo.api.events.furniture.NexoFurnitureBreakEvent
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener

class NexoObjectiveListener(
    private val objectives: Collection<NexoObjective>
) : Listener {

    @EventHandler
    fun NexoFurnitureBreakEvent.onNxFurnitureBreak() {
        println("NexoFurnitureBreakEvent detected")
        val id = NexoFurniture.furnitureMechanic(baseEntity)?.itemID ?: return
        handle(
            ActionType.BREAK,
            TargetType.FURNITURE,
            id,
            player
        )
    }

    private fun handle(
        action: ActionType,
        target: TargetType,
        itemId: String,
        player: Player
    ) {
        objectives
            .asSequence()
            .filter { it.matches(action, target) }
            .forEach { it.tryProgress(player, itemId) }
    }

}