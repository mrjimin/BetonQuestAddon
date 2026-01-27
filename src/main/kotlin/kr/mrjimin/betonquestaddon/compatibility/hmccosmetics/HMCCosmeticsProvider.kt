package kr.mrjimin.betonquestaddon.compatibility.hmccosmetics

import com.hibiscusmc.hmccosmetics.api.HMCCosmeticsAPI
import kr.mrjimin.betonquestaddon.provider.cosmetics.CosmeticsProvider
import kr.mrjimin.betonquestaddon.provider.cosmetics.CosmeticsWrapper
import org.bukkit.entity.Player
import java.util.concurrent.ConcurrentHashMap

object HMCCosmeticsProvider : CosmeticsProvider {

    private val cache = ConcurrentHashMap<String, CosmeticsWrapper>()

    override fun exists(key: String): Boolean = get(key) != null

    override fun getAll(): List<CosmeticsWrapper> =
        HMCCosmeticsAPI.getAllCosmetics().mapNotNull { get(it.id) }

    override fun get(key: String): CosmeticsWrapper? {
        return cache.getOrPut(key) {
            val cosmetic = HMCCosmeticsAPI.getCosmetic(key) ?: return@getOrPut null
            val item = cosmetic.item ?: return@getOrPut null
            CosmeticsWrapper(cosmetic.id, cosmetic.permission, item.clone())
        }
    }

    override fun owns(player: Player, key: String): Boolean {
        val wrapper = get(key) ?: return false
        return player.hasPermission(wrapper.permission)
    }

    override fun getEquipped(player: Player): List<CosmeticsWrapper> {
        val user = HMCCosmeticsAPI.getUser(player.uniqueId) ?: return emptyList()
        return HMCCosmeticsAPI.getAllCosmeticSlots().values.mapNotNull { slot ->
            user.getCosmetic(slot)?.id?.let { get(it) }
        }
    }

    override fun equip(player: Player, key: String): Boolean {
        val user = HMCCosmeticsAPI.getUser(player.uniqueId) ?: return false
        val cosmetic = HMCCosmeticsAPI.getCosmetic(key) ?: return false
        user.addCosmetic(cosmetic)
        return true
    }

    override fun unequip(player: Player, key: String): Boolean {
        val user = HMCCosmeticsAPI.getUser(player.uniqueId) ?: return false
        val cosmetic = HMCCosmeticsAPI.getCosmetic(key) ?: return false
        user.removeCosmeticSlot(cosmetic)
        return true
    }

    override fun isInWardrobe(player: Player): Boolean {
        val user = HMCCosmeticsAPI.getUser(player.uniqueId) ?: return false
        return user.isInWardrobe
    }

    fun clearCache() {
        cache.clear()
    }
}