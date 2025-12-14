package com.github.mrjimin.betonquestaddon.compatibilityold.customcrops.objectives

import com.github.mrjimin.betonquestaddon.compatibilityold.BaseObjective
import com.github.mrjimin.betonquestaddon.compatibilityold.LangMessageKey
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
        if (!crops.getValue(profile).contains(cropStageItemID())) return
        handle(player)
    }

}