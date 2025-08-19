package com.github.mrjimin.betonquestaddon.hook

import com.github.mrjimin.betonquestaddon.compatibility.craftengine.CeParser
import com.github.mrjimin.betonquestaddon.util.server.NotFoundPlugin
import com.github.mrjimin.betonquestaddon.util.server.checkPlugin
import com.github.mrjimin.betonquestaddon.util.getBoolean
import net.momirealms.craftengine.bukkit.api.BukkitAdaptors
import net.momirealms.craftengine.bukkit.api.CraftEngineBlocks
import net.momirealms.craftengine.bukkit.api.CraftEngineFurniture
import net.momirealms.craftengine.bukkit.api.CraftEngineItems
import net.momirealms.craftengine.bukkit.entity.furniture.BukkitFurniture
import net.momirealms.craftengine.bukkit.world.BukkitBlockInWorld
import net.momirealms.craftengine.core.item.CustomItem
import net.momirealms.craftengine.core.util.Key
import org.betonquest.betonquest.api.instruction.Instruction
import org.betonquest.betonquest.api.instruction.argument.Argument
import org.betonquest.betonquest.api.instruction.variable.Variable
import org.betonquest.betonquest.api.quest.event.PlayerEvent
import org.betonquest.betonquest.quest.PrimaryServerThreadData
import org.betonquest.betonquest.quest.event.PrimaryServerThreadEvent
import org.bukkit.Location
import org.bukkit.entity.Entity
import org.bukkit.inventory.ItemStack

object CraftEngineHook {

    init {
        if (!"CraftEngine".checkPlugin()) throw NotFoundPlugin("CraftEngine")
    }

    fun Key?.toIdOrNull(): String? = this?.let { "${it.namespace}:${it.value}" }

    fun String.toKeyOrNull(): Key? {
        val (namespace, key) = split(":").takeIf { it.size == 2 } ?: return null
        return Key(namespace, key)
    }

    fun itemFromId(id: String): CustomItem<ItemStack>? =
        id.toKeyOrNull()?.let { CraftEngineItems.byId(it) }

    fun itemFromKey(key: Key): CustomItem<ItemStack>? =
        CraftEngineItems.byId(key)

    fun exists(id: String): Boolean =
        id.toKeyOrNull()?.let { CraftEngineItems.byId(it) != null } ?: false

    fun exists(key: Key): Boolean = CraftEngineItems.byId(key) != null

    fun exists(item: ItemStack): Boolean =
        item.let { CraftEngineItems.isCustomItem(it) }

    fun customItemFromItem(item: ItemStack?): CustomItem<ItemStack>? =
        item?.let(CraftEngineItems::byItemStack)

    fun keyFromItem(item: ItemStack?): Key? =
        item?.let(CraftEngineItems::getCustomItemId)

    fun idFromItem(item: ItemStack?): String? =
        keyFromItem(item).toIdOrNull()

    // Block
    fun isCustomBlock(id: String): Boolean =
        id.toKeyOrNull()?.let { CraftEngineBlocks.byId(it) != null } ?: false

    fun isCustomBlock(key: Key): Boolean =
        key.let { CraftEngineBlocks.byId(it) != null }

    fun customBlockAdapt(location: Location): BukkitBlockInWorld? {
        return BukkitAdaptors.adapt(location.block)
    }

    // Furniture
    fun isFurniture(id: String): Boolean =
        id.toKeyOrNull()?.let { CraftEngineFurniture.byId(it) != null } ?: false

    fun isFurniture(key: Key): Boolean =
        key.let { CraftEngineFurniture.byId(it) != null }

    fun furnitureAdapt(entity: Entity): BukkitFurniture? {
        return CraftEngineFurniture.getLoadedFurnitureByBaseEntity(entity)
    }

//    fun furnitureAdapt(location: Location): BukkitFurniture? {
//        return location.e
//    }

    // Util
    fun create(
        instruction: Instruction,
        data: PrimaryServerThreadData,
        builder: (Variable<String>, Variable<Location>, Variable<Boolean>) -> PlayerEvent
    ): PlayerEvent {
        val itemID: Variable<String> = instruction.get(CeParser)
        val location: Variable<Location> = instruction.get(Argument.LOCATION)
        val playSound: Variable<Boolean> = instruction.getBoolean("playSound", false)
        return PrimaryServerThreadEvent(builder(itemID, location, playSound), data)
    }

}