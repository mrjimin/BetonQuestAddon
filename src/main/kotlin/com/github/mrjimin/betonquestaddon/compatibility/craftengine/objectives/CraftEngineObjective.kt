package com.github.mrjimin.betonquestaddon.compatibility.craftengine.objectives

import com.github.mrjimin.betonquestaddon.util.action.ActionType
import net.momirealms.craftengine.bukkit.api.BukkitAdaptors
import net.momirealms.craftengine.bukkit.api.CraftEngineFurniture
import net.momirealms.craftengine.bukkit.api.event.*
import org.betonquest.betonquest.api.CountingObjective
import org.betonquest.betonquest.api.QuestException
import org.betonquest.betonquest.api.instruction.Argument
import org.betonquest.betonquest.api.quest.objective.event.ObjectiveFactoryService
import org.bukkit.block.Block
import org.bukkit.entity.Entity
import org.bukkit.entity.Player
import kotlin.jvm.Throws

class CraftEngineObjective(
    service: ObjectiveFactoryService,
    targetAmount: Argument<Number>,
    actionType: ActionType,
    private val identifier: Argument<String>
) : CountingObjective(service, targetAmount, "betonquestaddon.${actionType.toKey()}") {

    fun onBlockPlace(event: CustomBlockPlaceEvent) {
        handleBlock(event.player, event.bukkitBlock())
    }

    fun onBlockBreak(event: CustomBlockBreakEvent) {
        handleBlock(event.player, event.bukkitBlock())
    }

    fun onBlockInteract(event: CustomBlockInteractEvent) {
        handleBlock(event.player, event.bukkitBlock())
    }

    fun onFurniturePlace(event: FurniturePlaceEvent) {
        handleFurniture(event.player, event.furniture().bukkitEntity)
    }

    fun onFurnitureBreak(event: FurnitureBreakEvent) {
        handleFurniture(event.player, event.furniture().bukkitEntity)
    }

    fun onFurnitureInteract(event: FurnitureInteractEvent) {
        handleFurniture(event.player, event.furniture().bukkitEntity)
    }

    @Throws(QuestException::class)
    private fun handleBlock(player: Player, block: Block) {
        val profile = service.profileProvider.getProfile(player) ?: return
        if (!service.containsProfile(profile) || !service.checkConditions(profile)) return

        val blockId = BukkitAdaptors.adapt(block).id().toString()
        if (identifier.getValue(profile) == blockId) {
            getCountingData(profile)?.progress()
            completeIfDoneOrNotify(profile)
        }
    }

    @Throws(QuestException::class)
    private fun handleFurniture(player: Player, entity: Entity) {
        val profile = service.profileProvider.getProfile(player) ?: return
        if (!service.containsProfile(profile) || !service.checkConditions(profile)) return

        val furnitureId = CraftEngineFurniture.getLoadedFurnitureBySeat(entity)?.id().toString()
        if (identifier.getValue(profile) == furnitureId) {
            getCountingData(profile)?.progress()
            completeIfDoneOrNotify(profile)
        }
    }

}