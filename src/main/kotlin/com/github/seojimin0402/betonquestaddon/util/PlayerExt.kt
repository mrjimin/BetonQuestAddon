package com.github.seojimin0402.betonquestaddon.util

import org.bukkit.Bukkit
import org.bukkit.OfflinePlayer
import org.bukkit.entity.Player

fun OfflinePlayer.toOnlinePlayer(): Player =
    requireNotNull(Bukkit.getPlayer(uniqueId))