package com.github.mrjimin.betonquestaddon.compatibility

import org.betonquest.betonquest.BetonQuest
import org.betonquest.betonquest.api.CountingObjective
import org.betonquest.betonquest.api.logger.BetonQuestLogger
import org.betonquest.betonquest.api.profile.OnlineProfile
import org.betonquest.betonquest.instruction.Instruction
import org.betonquest.betonquest.instruction.variable.Variable
import org.bukkit.Bukkit
import org.bukkit.entity.Player
import org.bukkit.event.HandlerList
import org.bukkit.event.Listener

abstract class AbstractBaseObjective(
    protected val instruction: Instruction,
    targetAmount: Variable<Number>,
    langMessageKey: LangMessageKey,
    protected val log: BetonQuestLogger
) : CountingObjective(instruction, targetAmount, langMessageKey.key), Listener {

    override fun start() {
        Bukkit.getPluginManager().registerEvents(this, BetonQuest.getInstance())
    }

    override fun stop() {
        HandlerList.unregisterAll(this)
    }

    protected fun getProfile(player: Player?): OnlineProfile =
        BetonQuest.getInstance().profileProvider.getProfile(player)
}
