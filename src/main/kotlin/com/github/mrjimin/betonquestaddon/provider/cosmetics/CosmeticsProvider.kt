package com.github.mrjimin.betonquestaddon.provider.cosmetics

import org.bukkit.entity.Player

interface CosmeticsProvider {
    fun exists(key: String): Boolean
    fun getAll(): List<CosmeticsWrapper>
    fun get(key: String): CosmeticsWrapper?

    fun owns(player: Player, key: String): Boolean

    fun getEquipped(player: Player): List<CosmeticsWrapper>

    fun equip(player: Player, key: String): Boolean
    fun unequip(player: Player, key: String): Boolean

    fun isInWardrobe(player: Player): Boolean
}