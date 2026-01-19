package com.github.mrjimin.betonquestaddon.compatibility.cosmeticscore

import com.github.mrjimin.betonquestaddon.provider.cosmetics.CosmeticsProvider
import com.github.mrjimin.betonquestaddon.provider.cosmetics.CosmeticsWrapper
import dev.lone.cosmeticscore.api.temporary.CosmeticAccessor
import dev.lone.cosmeticscore.api.temporary.CosmeticsCoreApi
import org.bukkit.Bukkit
import org.bukkit.entity.Player
import java.util.concurrent.ConcurrentHashMap

object CosmeticsCoreProvider : CosmeticsProvider {

    private val cache = ConcurrentHashMap<String, CosmeticsWrapper>()

    override fun exists(key: String): Boolean =
        CosmeticsCoreApi.isCosmeticRegistered(key)

    override fun getAll(): List<CosmeticsWrapper> {
        return getAllKeys().mapNotNull { get(it) }
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

    override fun owns(player: Player, key: String): Boolean =
        player.hasPermission(permission(key))

    override fun getEquipped(player: Player): List<CosmeticsWrapper> {
        return CosmeticsCoreApi.getEquippedCosmeticsAccessors(player)
            .filterIsInstance<CosmeticAccessor>()
            .mapNotNull { accessor ->
                cache.getOrPut(accessor.key) {
                    val item = accessor.guiModelItem ?: return@mapNotNull null
                    CosmeticsWrapper(accessor.key, permission(accessor.key), item.clone())
                }
            }
    }

    override fun equip(player: Player, key: String): Boolean {
        val accessor = newAccessor(key, player) ?: return false
        accessor.equip()
        return true
    }

    override fun unequip(player: Player, key: String): Boolean {
        val accessor = newAccessor(key, player) ?: return false
        accessor.unequip()
        return true
    }

    override fun isInWardrobe(player: Player): Boolean {
        return CosmeticsCoreApi.isInWardrobe(player)
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