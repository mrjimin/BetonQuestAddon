package com.github.mrjimin.betonquestaddon.compatibility.itemsadder.objectives

import com.github.mrjimin.betonquestaddon.util.action.ActionType
import dev.lone.itemsadder.api.CustomBlock
import dev.lone.itemsadder.api.CustomFurniture
import dev.lone.itemsadder.api.Events.*
import org.betonquest.betonquest.api.CountingObjective
import org.betonquest.betonquest.api.QuestException
import org.betonquest.betonquest.api.instruction.Argument
import org.betonquest.betonquest.api.quest.objective.event.ObjectiveFactoryService
import org.bukkit.block.Block
import org.bukkit.entity.Entity
import org.bukkit.entity.Player

class ItemsAdderObjective(
    service: ObjectiveFactoryService,
    targetAmount: Argument<Number>,
    actionType: ActionType,
    private val identifier: Argument<String>
) : CountingObjective(service, targetAmount, "betonquestaddon.${actionType.toKey()}") {

    fun onBlockPlace(event: CustomBlockPlaceEvent) {
        handleBlock(event.player, event.block)
    }

    fun onBlockBreak(event: CustomBlockBreakEvent) {
        handleBlock(event.player, event.block)
    }

    fun onBlockInteract(event: CustomBlockInteractEvent) {
        handleBlock(event.player, event.blockClicked)
    }

    fun onFurniturePlace(event: FurniturePlacedEvent) {
        handleFurniture(event.player, event.furniture?.entity!!)
    }

    fun onFurnitureBreak(event: FurnitureBreakEvent) {
        handleFurniture(event.player, event.furniture?.entity!!)
    }

    fun onFurnitureInteract(event: FurnitureInteractEvent) {
        handleFurniture(event.player, event.furniture?.entity!!)
    }

    @Throws(QuestException::class)
    private fun handleBlock(player: Player, block: Block) {
        val profile = service.profileProvider.getProfile(player) ?: return
        if (!service.containsProfile(profile) || !service.checkConditions(profile)) return

        val blockId = CustomBlock.byAlreadyPlaced(block)?.namespacedID
        if (identifier.getValue(profile) == blockId) {
            getCountingData(profile)?.progress()
            completeIfDoneOrNotify(profile)
        }
    }

    @Throws(QuestException::class)
    private fun handleFurniture(player: Player, entity: Entity) {
        val profile = service.profileProvider.getProfile(player) ?: return
        if (!service.containsProfile(profile) || !service.checkConditions(profile)) return

        val furnitureId = CustomFurniture.byAlreadySpawned(entity)?.namespacedID
        if (identifier.getValue(profile) == furnitureId) {
            getCountingData(profile)?.progress()
            completeIfDoneOrNotify(profile)
        }
    }

}