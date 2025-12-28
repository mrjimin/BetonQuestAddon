package com.github.mrjimin.betonquestaddon.compatibility.cosmetics

import org.bukkit.entity.Player

interface CosmeticsService {
    fun exists(key: String): Boolean
    fun getAll(): List<CosmeticsWrapper>
    fun get(key: String): CosmeticsWrapper?

    fun hasPermission(player: Player, key: String): Boolean
    fun owns(player: Player, key: String): Boolean
}