package com.github.mrjimin.betonquestaddon.hook

import me.clip.placeholderapi.PlaceholderAPI
import org.bukkit.entity.Player

object PAPIHook {

    fun String.resolvePapiRaw(player: Player) =
        PlaceholderAPI.setPlaceholders(player, this)

    fun String.toPapi(player: Player): String {
        val formatted = if (this.startsWith("%") && this.endsWith("%")) this else "%$this%"
        return PlaceholderAPI.setPlaceholders(player, formatted)
    }
}