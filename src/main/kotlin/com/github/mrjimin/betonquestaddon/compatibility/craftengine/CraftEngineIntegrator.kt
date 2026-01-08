package com.github.mrjimin.betonquestaddon.compatibility.craftengine

import com.github.mrjimin.betonquestaddon.compatibility.ICompatibility
import com.github.mrjimin.betonquestaddon.compatibility.craftengine.action.CraftEngineActionFactory
import com.github.mrjimin.betonquestaddon.compatibility.craftengine.item.CraftEngineItemFactory
import com.github.mrjimin.betonquestaddon.compatibility.craftengine.item.CraftEngineQuestItemSerializer
import com.github.mrjimin.betonquestaddon.compatibility.craftengine.objectives.CraftEngineObjectiveFactory
import com.github.mrjimin.betonquestaddon.conditions.BaseConditionFactory
import com.github.mrjimin.betonquestaddon.util.action.ActionType
import com.github.mrjimin.betonquestaddon.util.action.TargetType
import net.momirealms.craftengine.bukkit.api.BukkitAdaptors
import net.momirealms.craftengine.bukkit.api.CraftEngineFurniture
import org.betonquest.betonquest.api.BetonQuestApi

class CraftEngineIntegrator : ICompatibility {

    override fun hook(api: BetonQuestApi) {
        val questRegistries = api.questRegistries

        val itemRegistry = api.featureRegistries.item()
        itemRegistry.register("craftEngine", CraftEngineItemFactory())
        itemRegistry.registerSerializer("craftEngine", CraftEngineQuestItemSerializer())

        val condition = questRegistries.condition()
        condition.registerCombined(
            "craftEngineBlock",
            BaseConditionFactory {  location ->
                BukkitAdaptors.adapt(location.block).id().toString()
            }
        )
        condition.registerCombined(
            "craftFurniture",
            BaseConditionFactory { location ->
                location.world
                    .getNearbyEntities(location, 1.0, 1.0, 1.0).firstNotNullOfOrNull { entity ->
                        CraftEngineFurniture
                            .getLoadedFurnitureByMetaEntity(entity)
                            ?.id()
                            ?.toString()
                    }
            }
        )

        val action = questRegistries.action()
        action.register(
            "craftEngineBlockAt",
            CraftEngineActionFactory(TargetType.BLOCK)
        )
        action.register(
            "craftEngineFurnitureAt",
            CraftEngineActionFactory(TargetType.FURNITURE)
        )


        val objective = questRegistries.objective()
        objective.register("craftEngineBlockBreak", CraftEngineObjectiveFactory(TargetType.BLOCK, ActionType.BREAK))
        objective.register("craftEngineBlockPlace", CraftEngineObjectiveFactory(TargetType.BLOCK, ActionType.PLACE))
        objective.register("craftEngineBlockInteract", CraftEngineObjectiveFactory(TargetType.BLOCK, ActionType.INTERACT))

        objective.register("craftEngineFurnitureBreak", CraftEngineObjectiveFactory(TargetType.FURNITURE, ActionType.BREAK))
        objective.register("craftEngineFurniturePlace", CraftEngineObjectiveFactory(TargetType.FURNITURE, ActionType.PLACE))
        objective.register("craftEngineFurnitureInteract", CraftEngineObjectiveFactory(TargetType.FURNITURE, ActionType.INTERACT))
    }

}