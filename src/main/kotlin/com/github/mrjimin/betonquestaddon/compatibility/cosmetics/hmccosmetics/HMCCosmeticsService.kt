package com.github.mrjimin.betonquestaddon.compatibility.cosmetics.hmccosmetics

import com.github.mrjimin.betonquestaddon.compatibility.cosmetics.CosmeticsWrapper
import com.github.mrjimin.betonquestaddon.compatibility.cosmetics.CosmeticsService
import com.hibiscusmc.hmccosmetics.api.HMCCosmeticsAPI
import org.bukkit.entity.Player
import java.util.concurrent.ConcurrentHashMap

class HMCCosmeticsService : CosmeticsService {

    private val cache = ConcurrentHashMap<String, CosmeticsWrapper>()

    override fun exists(key: String): Boolean = get(key) != null

    override fun getAll(): List<CosmeticsWrapper> {
        val cosmetics = HMCCosmeticsAPI.getAllCosmetics()
        if (cosmetics.isEmpty()) return emptyList()

        return cosmetics.mapNotNull { cosmetic ->
            cache.getOrPut(cosmetic.id) {
                val item = cosmetic.item ?: return@mapNotNull null
                CosmeticsWrapper(cosmetic.id, cosmetic.permission, item.clone())
            }
        }
    }

    override fun get(key: String): CosmeticsWrapper? {
        cache[key]?.let { return it }

        return HMCCosmeticsAPI.getCosmetic(key)?.let { cosmetic ->
            val item = cosmetic.item ?: return null
            val wrapper = CosmeticsWrapper(cosmetic.id, cosmetic.permission, item.clone())
            cache[cosmetic.id] = wrapper
            wrapper
        }
    }

    override fun hasPermission(player: Player, key: String): Boolean =
        get(key)?.let { player.hasPermission(it.permission) } ?: false

    override fun owns(player: Player, key: String): Boolean {
        val user = HMCCosmeticsAPI.getUser(player.uniqueId) ?: return false
        val slots = HMCCosmeticsAPI.getAllCosmeticSlots().values

        return slots.any { slot -> user.getCosmetic(slot)?.id == key }
    }

    fun clearCache() {
        cache.clear()
    }
}