package com.github.mrjimin.betonquestaddon.hook

import com.github.mrjimin.betonquestaddon.util.getNumberNotLessThanZero
import org.betonquest.betonquest.api.Objective
import org.betonquest.betonquest.api.instruction.Instruction
import org.betonquest.betonquest.api.instruction.argument.Argument
import org.betonquest.betonquest.api.instruction.variable.Variable
import org.betonquest.betonquest.api.profile.Profile
import org.betonquest.betonquest.api.quest.QuestException
import org.bukkit.Location
import org.bukkit.entity.Player

object CustomCropsHook {

    fun isInvalidLocation(
        player: Player,
        profile: Profile,
        location: Variable<Location>?,
        range: Variable<Number>?
    ): Boolean {
        val locVar = location ?: return false
        val rangeVar = range ?: return false

        val targetLocation = try { locVar.getValue(profile) }
        catch (e: QuestException) { throw RuntimeException("Failed to get target location", e) }

        val rangeValue = try { rangeVar.getValue(profile).toInt() }
        catch (e: QuestException) { throw RuntimeException("Failed to get range", e) }

        val playerLoc = player.location
        return playerLoc.world != targetLocation.world || playerLoc.distanceSquared(targetLocation) > rangeValue * rangeValue
    }

    fun <T : Objective> create(
        instruction: Instruction,
        constructor: (Instruction, Variable<Number>, Variable<List<String>>, Variable<Location>?, Variable<Number>?) -> T
    ): T {
        val crops = instruction.getList { it }
        val targetAmount = instruction.getNumberNotLessThanZero("amount", 1)
        val location = instruction.getValue("location", Argument.LOCATION)
        val range = instruction.getValue("range", Argument.NUMBER)
        return constructor(instruction, targetAmount, crops, location, range)
    }
}