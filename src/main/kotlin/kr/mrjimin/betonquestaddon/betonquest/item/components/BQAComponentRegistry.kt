package kr.mrjimin.betonquestaddon.betonquest.item.components

import net.kyori.adventure.text.minimessage.MiniMessage
import org.betonquest.betonquest.api.QuestException

object BQAComponentRegistry {
    private val builders = mutableMapOf<String, (String) -> ItemComponents>()

    fun register(key: String, builder: (String) -> ItemComponents) {
        builders[key.lowercase()] = builder
    }

    fun create(key: String, data: String): ItemComponents {
        return builders[key.lowercase()]?.invoke(data)
            ?: throw QuestException("Unknown item component: $key")
    }
}