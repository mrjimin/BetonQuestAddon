package kr.mrjimin.betonquestaddon.config

import kr.mrjimin.betonquestaddon.betonquest.conversation.CloseButtonSettings
import kr.mrjimin.betonquestaddon.betonquest.conversation.DialogLayout
import kr.mrjimin.betonquestaddon.betonquest.conversation.DialogSettings
import org.bukkit.configuration.file.FileConfiguration

class DialogConfig(private val config: FileConfiguration) {

    val settings: DialogSettings
        get() {
            val section = config.getConfigurationSection("conversation.dialog")

            val layout = section?.getString("layout")?.uppercase()
                ?.let { runCatching { DialogLayout.valueOf(it) }.getOrDefault(DialogLayout.NPC_TITLE) }
                ?: DialogLayout.NPC_TITLE

            val closeSection = section?.getConfigurationSection("close-button")
            val closeButton = CloseButtonSettings(
                enabled = closeSection?.getBoolean("enabled") ?: true,
                text = closeSection?.getString("text") ?: "Close",
                closeWithEscape = closeSection?.getBoolean("close-with-escape") ?: true
            )

            return DialogSettings(layout = layout, closeButton = closeButton)
        }
}