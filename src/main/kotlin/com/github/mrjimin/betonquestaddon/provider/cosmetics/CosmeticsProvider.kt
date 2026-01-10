package com.github.mrjimin.betonquestaddon.provider.cosmetics

import org.bukkit.entity.Player

interface CosmeticsProvider {
    fun exists(key: String): Boolean
    fun getAll(): List<CosmeticsWrapper>
    fun get(key: String): CosmeticsWrapper?

    fun hasPermission(player: Player, key: String): Boolean
    fun owns(player: Player, key: String): Boolean
}