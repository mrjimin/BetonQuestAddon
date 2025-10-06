package com.github.mrjimin.betonquestaddon.compatibility.customcrops.objectives

import com.github.mrjimin.betonquestaddon.compatibility.BaseObjective
import com.github.mrjimin.betonquestaddon.compatibility.LangMessageKey
import com.github.mrjimin.betonquestaddon.hook.CustomCropsHook
import net.momirealms.customcrops.api.event.CropPlantEvent
import org.betonquest.betonquest.api.instruction.Instruction
import org.betonquest.betonquest.api.instruction.variable.Variable
import org.bukkit.Location
import org.bukkit.event.EventHandler

class CCropsPlantObjective(
    instruction: Instruction,
    targetAmount: Variable<Number>,
    private val crops: Variable<List<String>>,
    private val location: Variable<Location>? = null,
    private val range: Variable<Number>? = null
) : BaseObjective(instruction, targetAmount, LangMessageKey.CROP_PLANT) {

    @EventHandler(ignoreCancelled = true)
    fun CropPlantEvent.onCropPlantEvent() {
        val profile = getProfile(player) ?: return

        if (CustomCropsHook.isInvalidLocation(player, profile, location, range)) return
        println(crops.getValue(profile))
        println(!crops.getValue(profile).contains(cropConfig().id()))
        if (!crops.getValue(profile).contains(cropConfig().id())) return
        handle(player)
    }
}