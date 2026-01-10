package com.github.mrjimin.betonquestaddon.compatibility.nexo.objectives

import com.github.mrjimin.betonquestaddon.util.action.ActionType
import com.nexomc.nexo.api.NexoBlocks
import com.nexomc.nexo.api.NexoFurniture
import com.nexomc.nexo.api.events.custom_block.*
import com.nexomc.nexo.api.events.furniture.*
import org.betonquest.betonquest.api.CountingObjective
import org.betonquest.betonquest.api.QuestException
import org.betonquest.betonquest.api.instruction.Argument
import org.betonquest.betonquest.api.quest.objective.event.ObjectiveFactoryService
import org.bukkit.block.Block
import org.bukkit.entity.Entity
import org.bukkit.entity.Player

class NexoObjective(
    service: ObjectiveFactoryService,
    targetAmount: Argument<Number>,
    actionType: ActionType,
    private val identifier: Argument<String>
) : CountingObjective(service, targetAmount, "betonquestaddon.${actionType.toKey()}") {

    fun onBlockPlace(event: NexoBlockPlaceEvent) {
        handleBlock(event.player, event.block)
    }

    fun onBlockBreak(event: NexoBlockBreakEvent) {
        handleBlock(event.player, event.block)
    }

    fun onBlockInteract(event: NexoBlockInteractEvent) {
        handleBlock(event.player, event.block)
    }

    fun onFurniturePlace(event: NexoFurniturePlaceEvent) {
        handleFurniture(event.player, event.baseEntity)
    }

    fun onFurnitureBreak(event: NexoFurnitureBreakEvent) {
        handleFurniture(event.player, event.baseEntity)
    }

    fun onFurnitureInteract(event: NexoFurnitureInteractEvent) {
        handleFurniture(event.player, event.baseEntity)
    }

    @Throws(QuestException::class)
    private fun handleBlock(player: Player, block: Block) {
        val profile = service.profileProvider.getProfile(player) ?: return
        if (!service.containsProfile(profile) || !service.checkConditions(profile)) return

        val blockId = NexoBlocks.customBlockMechanic(block)?.itemID
        if (identifier.getValue(profile) == blockId) {
            getCountingData(profile)?.progress()
            completeIfDoneOrNotify(profile)
        }
    }

    @Throws(QuestException::class)
    private fun handleFurniture(player: Player, entity: Entity) {
        val profile = service.profileProvider.getProfile(player) ?: return
        if (!service.containsProfile(profile) || !service.checkConditions(profile)) return

        val furnitureId = NexoFurniture.furnitureMechanic(entity)?.itemID
        if (identifier.getValue(profile) == furnitureId) {
            getCountingData(profile)?.progress()
            completeIfDoneOrNotify(profile)
        }
    }
}