package com.github.mrjimin.betonquestaddon.compatibility.customcrops.objectives

import com.github.mrjimin.betonquestaddon.compatibility.BaseObjective
import com.github.mrjimin.betonquestaddon.compatibility.LangMessageKey
import com.github.mrjimin.betonquestaddon.hook.CustomCropsHook
import net.momirealms.customcrops.api.event.CropBreakEvent
import org.betonquest.betonquest.api.instruction.Instruction
import org.betonquest.betonquest.api.instruction.variable.Variable
import org.bukkit.Location
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler

class CCropsHarvestObjective(
    instruction: Instruction,
    targetAmount: Variable<Number>,
    private val crops: Variable<List<String>>,
    private val location: Variable<Location>? = null,
    private val range: Variable<Number>? = null
) : BaseObjective(instruction, targetAmount, LangMessageKey.CROP_HARVEST) {

    @EventHandler(ignoreCancelled = true)
    fun CropBreakEvent.onCropBreakEvent() {
        val player = entityBreaker() as Player
        val profile = getProfile(player) ?: return

        if (CustomCropsHook.isInvalidLocation(player, profile, location, range)) return
        println(crops.getValue(profile))
        println(!crops.getValue(profile).contains(cropConfig().id()))
        println(!crops.getValue(profile).contains(cropStageItemID()))
        if (!crops.getValue(profile).contains(cropStageItemID())) return
        handle(player)
    }

}