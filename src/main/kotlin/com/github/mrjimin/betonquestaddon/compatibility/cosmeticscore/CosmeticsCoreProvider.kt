package com.github.mrjimin.betonquestaddon.compatibility.cosmeticscore

import com.github.mrjimin.betonquestaddon.provider.cosmetics.CosmeticsProvider
import com.github.mrjimin.betonquestaddon.provider.cosmetics.CosmeticsWrapper
import dev.lone.cosmeticscore.api.temporary.CosmeticAccessor
import dev.lone.cosmeticscore.api.temporary.CosmeticsCoreApi
import org.bukkit.Bukkit
import org.bukkit.entity.Player
import java.util.concurrent.ConcurrentHashMap

class CosmeticsCoreProvider : CosmeticsProvider {

    private val cache = ConcurrentHashMap<String, CosmeticsWrapper>()

    override fun exists(key: String): Boolean = get(key) != null

    override fun getAll(): List<CosmeticsWrapper> {
        val keys = getAllKeys()
        if (keys.isEmpty()) return emptyList()
        return keys.mapNotNull { get(it) }
    }

    override fun get(key: String): CosmeticsWrapper? {
        cache[key]?.let { return it }

        val player = anyPlayer() ?: return null
        return newAccessor(key, player)?.guiModelItem?.let { item ->
            val wrapper = CosmeticsWrapper(key, permission(key), item.clone())
            cache[key] = wrapper
            wrapper
        }
    }

    override fun hasPermission(player: Player, key: String): Boolean =
        get(key)?.let { player.hasPermission(it.permission) } ?: false

    override fun owns(player: Player, key: String): Boolean =
        getEquipped(player).any { it.key == key }

    private fun getEquipped(player: Player): List<CosmeticsWrapper> {
        return CosmeticsCoreApi.getEquippedCosmeticsAccessors(player)
            .filterIsInstance<CosmeticAccessor>()
            .mapNotNull { accessor ->
                cache.getOrPut(accessor.key) {
                    val item = accessor.guiModelItem ?: return@mapNotNull null
                    CosmeticsWrapper(accessor.key, permission(accessor.key), item.clone())
                }
            }
    }

    private fun permission(key: String) = "cosmeticscore.user.cosmetics.wear.$key"

    private fun newAccessor(key: String, player: Player): CosmeticAccessor? =
        CosmeticsCoreApi.newCosmeticAccessor(key, player)

    @Suppress("UNCHECKED_CAST")
    private fun getAllKeys(): List<String> =
        CosmeticsCoreApi.getCosmeticsKeysCopy() as? List<String> ?: emptyList()

    private fun anyPlayer(): Player? = Bukkit.getOnlinePlayers().firstOrNull()

    fun clearCache() {
        cache.clear()
    }
}