package com.github.mrjimin.betonquestaddon.compatibility.craftengine

import com.github.mrjimin.betonquestaddon.compatibility.ICompatibility
import com.github.mrjimin.betonquestaddon.compatibility.craftengine.action.CraftEngineActionFactory
import com.github.mrjimin.betonquestaddon.compatibility.craftengine.item.CraftEngineItemFactory
import com.github.mrjimin.betonquestaddon.compatibility.craftengine.item.CraftEngineQuestItemSerializer
import com.github.mrjimin.betonquestaddon.compatibility.craftengine.objectives.CraftEngineObjectiveFactory
import com.github.mrjimin.betonquestaddon.conditions.LocationConditionFactory
import com.github.mrjimin.betonquestaddon.util.action.ActionType
import com.github.mrjimin.betonquestaddon.util.action.TargetType
import net.momirealms.craftengine.bukkit.api.BukkitAdaptors
import net.momirealms.craftengine.bukkit.api.CraftEngineFurniture
import org.betonquest.betonquest.api.BetonQuestApi

class CraftEngineIntegrator : ICompatibility {

    override fun hook(api: BetonQuestApi) {
        val questRegistries = api.questRegistries

        api.featureRegistries.item().apply {
            register("craftEngine", CraftEngineItemFactory())
            registerSerializer("craftEngine", CraftEngineQuestItemSerializer())
        }

        questRegistries.condition().apply {
            registerCombined("craftEngineBlock", LocationConditionFactory { location ->
                    BukkitAdaptors.adapt(location.block).id().toString()
                }
            )
            registerCombined("craftFurniture", LocationConditionFactory { location ->
                    location.world.getNearbyEntities(location, 1.0, 1.0, 1.0).firstNotNullOfOrNull { entity ->
                            CraftEngineFurniture.getLoadedFurnitureByMetaEntity(entity)
                                ?.id().toString()
                        }
                }
            )
        }

        questRegistries.action().apply {
            register("craftEngineBlockAt", CraftEngineActionFactory(TargetType.BLOCK))
            register("craftEngineFurnitureAt", CraftEngineActionFactory(TargetType.FURNITURE))
        }

        questRegistries.objective().apply {
            register("craftEngineBlockPlace", CraftEngineObjectiveFactory(ActionType.PLACE_BLOCK))
            register("craftEngineBlockBreak", CraftEngineObjectiveFactory(ActionType.BREAK_BLOCK))
            register("craftEngineBlockInteract", CraftEngineObjectiveFactory(ActionType.INTERACT_BLOCK))

            register("craftEngineFurniturePlace", CraftEngineObjectiveFactory(ActionType.PLACE_FURNITURE))
            register("craftEngineFurnitureBreak", CraftEngineObjectiveFactory(ActionType.BREAK_FURNITURE))
            register("craftEngineFurnitureInteract", CraftEngineObjectiveFactory(ActionType.INTERACT_FURNITURE))
        }
    }

}