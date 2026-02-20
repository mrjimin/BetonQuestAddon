package kr.mrjimin.betonquestaddon.compatibility.craftengine

import kr.mrjimin.betonquestaddon.betonquest.condition.LocationConditionFactory
import kr.mrjimin.betonquestaddon.compatibility.ICompatibility
import kr.mrjimin.betonquestaddon.compatibility.craftengine.action.CraftEngineSetBlockActionFactory
import kr.mrjimin.betonquestaddon.compatibility.craftengine.action.CraftEngineSetFurnitureActionFactory
import kr.mrjimin.betonquestaddon.compatibility.craftengine.item.CraftEngineItemFactory
import kr.mrjimin.betonquestaddon.compatibility.craftengine.item.CraftEngineQuestItemSerializer
import kr.mrjimin.betonquestaddon.compatibility.craftengine.objective.CraftEngineBlockObjectiveFactory
import kr.mrjimin.betonquestaddon.compatibility.craftengine.objective.CraftEngineFurnitureObjectiveFactory
import kr.mrjimin.betonquestaddon.config.NotifyMessage
import kr.mrjimin.betonquestaddon.util.action.Action
import net.momirealms.craftengine.bukkit.api.BukkitAdaptors
import net.momirealms.craftengine.bukkit.api.CraftEngineFurniture
import org.betonquest.betonquest.api.BetonQuestApi

class CraftEngineIntegrator : ICompatibility {

    override fun hook(api: BetonQuestApi) {
        api.items().registry().apply {
            register("craftEngine", CraftEngineItemFactory())
            registerSerializer("craftEngine", CraftEngineQuestItemSerializer())
        }

        api.conditions().registry().apply {
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

        api.actions().registry().apply {
            register("craftEngineBlockAt", CraftEngineSetBlockActionFactory())
            register("craftEngineFurnitureAt", CraftEngineSetFurnitureActionFactory())
        }

        api.objectives().registry().apply {
            register("craftEngineBlockPlace", CraftEngineBlockObjectiveFactory(Action.PLACE, NotifyMessage.BLOCK_PLACE))
            register("craftEngineBlockBreak", CraftEngineBlockObjectiveFactory(Action.BREAK, NotifyMessage.BLOCK_BREAK))
            register("craftEngineBlockInteract", CraftEngineBlockObjectiveFactory(Action.INTERACT, NotifyMessage.BLOCK_INTERACT))

            register("craftEngineFurniturePlace", CraftEngineFurnitureObjectiveFactory(Action.PLACE, NotifyMessage.FURNITURE_PLACE))
            register("craftEngineFurnitureBreak", CraftEngineFurnitureObjectiveFactory(Action.BREAK, NotifyMessage.FURNITURE_BREAK))
            register("craftEngineFurnitureInteract", CraftEngineFurnitureObjectiveFactory(Action.INTERACT, NotifyMessage.FURNITURE_INTERACT))
        }
    }

}